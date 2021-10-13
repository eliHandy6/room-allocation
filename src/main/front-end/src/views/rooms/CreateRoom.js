import React, { useState, useEffect } from 'react'
import { useHistory  } from 'react-router-dom'
import {
  CAlert,
  CCard,
  CCardBody,
  CCol,
  CRow,
  CCardGroup,
} from '@coreui/react'

import { Formik, Form, useField } from 'formik';
import * as Yup from 'yup';
import axios from 'axios';

const MyTextInput = ({ label, ...props }) => {
  const [field, meta] = useField(props);
  return (
    <>
      <label htmlFor={props.id || props.name}>{label}</label>
      <input className="text-input" {...field} {...props} />
      {meta.touched && meta.error ? (
        <div className="error">{meta.error}</div>
      ) : null}
    </>
  );
};

const MyCheckbox = ({ children, ...props }) => {
  const [field, meta] = useField({ ...props, type: 'checkbox' });
  return (
    <div>
      <label className="checkbox-input">
        <input type="checkbox" {...field} {...props} />
        {children}
      </label>
      {meta.touched && meta.error ? (
        <div className="error">{meta.error}</div>
      ) : null}
    </div>
  );
};

const MySelect = ({ label, ...props }) => {
  const [field, meta] = useField(props);
  return (
    <div>
      <label htmlFor={props.id || props.name}>{label}</label>
      <br />
      <select {...field} {...props} />
      {meta.touched && meta.error ? (
        <div className="error">{meta.error}</div>
      ) : null}
    </div>
  );
};


const BUILDING_API_BASE_URL = "http://localhost:9000/apis/v1/buildings";
const ROOMS_API_BASE_URL = "http://localhost:9000/apis/v1/rooms";
const fields = ['id', 'name']

const CreateRoom = () => {
  const history = useHistory();
  const [buildingArray, setBuildingArray] = useState([]);

  useEffect(() => {
    axios.get(BUILDING_API_BASE_URL)
      .then(res => {
        setBuildingArray(res.data.data_list[0])
      })
      .catch(err => {

      })
  }, [])

  if (!buildingArray) {
    return <h1>Buildings not found</h1>
  }

  return (
    <div>
      <CRow className="justify-content-center">
        <CCol md="8">
          <CCardGroup>
            <CCard className="p-4">
              <CCardBody>
                <h3>Register a room!</h3>
                <Formik
                  initialValues={{
                    name: '',
                    building_id: '',
                    capacity: ''

                  }}

                  validationSchema={Yup.object({
                    name: Yup.string()
                      .max(15, 'Must be 15 characters or less')
                      .required('Required'),

                    building_id: Yup.string()
                      .max(20, 'Must be 20 characters or less')
                      .required('Required'),

                    capacity: Yup.string()
                      .max(20, 'Must be 20 characters or less')
                      .required('Required'),

                  })}

                  onSubmit={(values, { setSubmitting }) => {
                    // setTimeout(() => {
                    //   alert(JSON.stringify(values, null, 2));
                    //   setSubmitting(false);
                    // }, 400);

                    // }}
                    setSubmitting(true);
                    axios.post(ROOMS_API_BASE_URL, values)
                      .then(res => {
                        if (res.status == 201) {
                          alert("Room Successfully Saved");
                          history.push('/rooms/view');
                        }
                      })
                      .catch(err => {
                        console.log(err)
                      }).
                      finally(() => {
                        setSubmitting(false);
                      })

                  }}
                >
                  <Form>
                    <CRow className="justify-content-center">
                      <CCol md="6">
                        <MySelect label="Building" name="building_id">
                          {buildingArray.map((c) =>
                            <option key={c.id} value={c.id} type="number">{c.name} </option>
                          )}
                        </MySelect>
                      </CCol>
                    </CRow>
                    <CRow className="justify-content-center">
                      <CCol md="6">
                        <MyTextInput
                          label="Room Name"
                          name="name"
                          type="text"
                          placeholder="A5 "

                        />
                      </CCol>
                    </CRow>
                    <CRow className="justify-content-center">
                      <CCol md="6">
                        <MyTextInput
                          label="Room Capasity"
                          name="capacity"
                          type="number"
                          placeholder="50"

                        />
                      </CCol>
                    </CRow>
                    <br></br>
                    <CRow className="justify-content-center">
                      <CCol md="6">
                        <button type="submit">Submit</button>
                      </CCol>
                    </CRow>
                  </Form>

                </Formik>
              </CCardBody>
            </CCard>
          </CCardGroup>
        </CCol>
      </CRow>
    </div>

  );

};
export default CreateRoom
