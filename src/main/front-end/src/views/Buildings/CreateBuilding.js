import React from 'react'
import {
  CAlert,
  CCard,
  CCardBody,
  CCol,
  CRow,
  CCardGroup,
} from '@coreui/react'
import { useHistory  } from 'react-router-dom'
import { Formik, Form, useField } from 'formik';
import * as Yup from 'yup';
import axios  from 'axios'


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

const BUILDING_API_BASE_URL="http://localhost:9000/apis/v1/buildings";

const MySelect = ({ label, ...props }) => {
  const [field, meta] = useField(props);
  return (
    <div>
      <label htmlFor={props.id || props.name}>{label}</label>
      <select {...field} {...props} />
      {meta.touched && meta.error ? (
        <div className="error">{meta.error}</div>
      ) : null}
    </div>
  );
};

const CreateBuilding = () => {
  const history = useHistory();

  return (
    <div>
    <CRow className="justify-content-center">
    <CCol md="8">
      <CCardGroup>
        <CCard className="p-4">
          <CCardBody>
          <h3>Register Building!</h3>
          <Formik
            initialValues={{
              name: '',
              department: ''
              
            }}
   
            validationSchema={Yup.object({
              name: Yup.string()
                .max(15, 'Must be 15 characters or less')
                .required('Required'),

                department: Yup.string()
                .max(20, 'Must be 20 characters or less')
                .required('Required'),
  
            })}
   
            onSubmit={(values, { setSubmitting }) => {
              setSubmitting(true);
              axios.post(BUILDING_API_BASE_URL,values)
                .then(res=>{
                  if(res.status==201){
                        alert("Building Successfully Saved");
                        history.push('/buildings/view');         
                  }
                })
                .catch(err=>{
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
                <MyTextInput
                  label="Building Name"
                  name="name"
                  type="text"
                  placeholder="Student Center"
                />
              </CCol>

              <CCol md="6">
                <MyTextInput
                  label="Department"
                  name="department"
                  type="text"
                  placeholder="Mathematics "
                  className="mb-3"
                />
              </CCol>
            </CRow>
            
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
   
    };
export default CreateBuilding
