import React, { } from 'react'
import { useHistory } from 'react-router-dom'
import {
  CCard,
  CCardBody,
  CRow,
  CCardGroup,
  CCol

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
const CLUB_API_BASE_URL = "http://localhost:9000/apis/v1/clubs";
const CreateClub = () => {
  const history = useHistory();
  return (
    <div>
      <CRow className="justify-content-center">
        <CCol md="8">
          <CCardGroup>
            <CCard className="p-4">
              <CCardBody>
                <h3>Register A Club!</h3>

                <Formik
                  initialValues={{
                    name: '',
                    description: '',
                    contact_person_phone: '',
                    contact_person: '',
                    club_email: ''

                  }}

                  validationSchema={Yup.object({
                    name: Yup.string()
                      .max(15, 'Must be 15 characters or less')
                      .required('Required'),

                    description: Yup.string()
                      .max(20, 'Must be 20 characters or less')
                      .required('Required'),

                    contact_person_phone: Yup.string()
                      .max(20, 'Must be 20 characters or less')
                      .required('Required'),

                    contact_person: Yup.string()
                      .max(20, 'Must be 20 characters or less')
                      .required('Required'),

                    club_email: Yup.string()
                      .max(20, 'Must be 20 characters or less')
                      .email('Email Must be Valid')
                      .required('Required'),

                  })}

                  onSubmit={(values, { setSubmitting }) => {
                    setSubmitting(true);
                    axios.post(CLUB_API_BASE_URL, values)
                      .then(res => {
                        if (res.status == 201) {
                          alert("Club Successfully Saved");
                          history.push('/clubs/view');
                        }
                      })
                      .catch(err => {
                        console.log(err)
                      }).
                      finally(() => {
                        setSubmitting(false);
                      })

                    // setTimeout(() => {
                    //   alert(JSON.stringify(values, null, 2));
                    //   setSubmitting(false);
                    // }, 400);

                  }}

                >
                  <Form>
                    <CRow className="justify-content-center">
                      <CCol md="6">
                        <MyTextInput
                          label="Club Name"
                          name="name"
                          type="text"
                          placeholder="Debating Club"
                        />
                      </CCol>

                      <CCol md="6">
                        <MyTextInput
                          label="Club Description"
                          name="description"
                          type="text"
                          placeholder="Students to Improve their English "
                          className="mb-3"
                        />
                      </CCol>
                    </CRow>

                    <CRow className="justify-content-center">
                      <CCol md="6">
                        <MyTextInput
                          label="Club Email"
                          name="club_email"
                          type="text"
                          placeholder="club1@gmail.com"
                        />
                      </CCol>

                      <CCol md="6">
                        <MyTextInput
                          label="Club Contact Person"
                          name="contact_person"
                          type="text"
                          placeholder="Apopo Tola "

                        />
                      </CCol>
                    </CRow>

                    <CRow >
                      <CCol md="6">
                        <MyTextInput
                          label="Club Contact phone number"
                          name="contact_person_phone"
                          type="text"
                          placeholder="07XXXXXXXX"
                        />
                      </CCol>
                    </CRow>

                    <br />
                    <button type="submit">Submit</button>
                  </Form>

                </Formik>
              </CCardBody>
            </CCard>
          </CCardGroup>
        </CCol>
      </CRow>
    </div>

  );
}

export default CreateClub
