import React, { useState, useEffect } from 'react'
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

const CLUB_API_BASE_URL = "http://localhost:9000/apis/v1/clubs";
const ROOM_API_BASE_URL = "http://localhost:9000/apis/v1/rooms";
const BOOKING_API_BASE_URL ="http://localhost:9000/apis/v1/bookings";


const CreateBooking = () => {

  const history = useHistory();
  const [clubsArray, setClubs] = useState([]);
  const [roomsArray, setRoomsArray] = useState([]);

  useEffect(() => {
    axios.get(CLUB_API_BASE_URL)
      .then(res => {
        setClubs(res.data.data_list[0])
      })
      .catch(err => {
      })

    axios.get(ROOM_API_BASE_URL)
      .then(res => {
        setRoomsArray(res.data.data_list[0])
      })
      .catch(err => {
      })
  },[])

  if (!clubsArray) {
    return <h1>No Club Found</h1>
  }

  return (
    <div>
      <CRow className="justify-content-center">
        <CCol md="8">
          <CCardGroup>
            <CCard className="p-4">
              <CCardBody>
                <h3>Book a room!</h3>
                <Formik
                  initialValues={{
                    club_id: '',
                    room_id: '',
                    venue_date: '',
                    time_from: '',
                    time_to: '',
                    number_of_guests: '',
                    bookie_comments: ''

                  }}

                  validationSchema={Yup.object({
                    club_id: Yup.string()
                      .max(15, 'Must be 15 characters or less')
                      .required('Required'),

                    room_id: Yup.string()
                      .max(20, 'Must be 20 characters or less')
                      .required('Required'),

                    venue_date: Yup.string()
                      .max(20, 'Must be 20 characters or less')
                      .required('Required'),

                    time_from: Yup.string()
                      .max(20, 'Must be 20 characters or less')
                      .required('Required'),

                    time_to: Yup.string()
                      .max(20, 'Must be 20 characters or less')
                      .required('Required'),

                    number_of_guests: Yup.string()
                      .max(20, 'Must be 20 characters or less')
                      .required('Required'),

                    bookie_comments: Yup.string()
                      .max(20, 'Must be 20 characters or less')
                      .required('Required'),

                  })}

                  onSubmit={(values, { setSubmitting }) => {
                    setSubmitting(true);
                    axios.post(BOOKING_API_BASE_URL, values)
                      .then(res => {
                        if (res.status == 201) {
                          alert("Booking Successfully Saved");
                          // history.push('/clubs/view');
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
                    <CRow className="">
                      <CCol md="6">
                        <MySelect label="Club Name" name="club_id">
                          {clubsArray.map((c) =>
                            <option key={c.id} value={c.id} type="number">{c.name} </option>
                          )}
                        </MySelect>
                      </CCol>

                      <CCol md="6">
                      <MySelect label="Room" name="room_id">
                          {roomsArray.map((c) =>
                            <option key={c.id} value={c.id} type="number">{c.name} </option>
                          )}
                        </MySelect>
                      </CCol>
                    </CRow>
                    <br/>
                    <CRow className="">
                      <CCol md="6">
                        <MyTextInput
                          label="Date"
                          name="venue_date"
                          type="date"
                          
                        />
                      </CCol>

                      <CCol md="6">
                        <MyTextInput
                          label="Time From"
                          name="time_from"
                          type="time"
                          placeholder="10:10"

                        />
                      </CCol>
                    </CRow>
                    <br/>

                    <CRow >
                      <CCol md="6">
                      <MyTextInput
                          label="Time To"
                          name="time_to"
                          type="time"
                          placeholder="10:30"

                        />
                      </CCol>

                      <CCol md="6">
                      <MyTextInput
                          label="Number of Guests"
                          name="number_of_guests"
                          type="number"
                          placeholder="10"

                        />
                      </CCol>
                    </CRow>

                    <CRow >
                      <CCol md="6">
                      <MyTextInput
                          label="Comments"
                          name="bookie_comments"
                          type="text"
                          placeholder="Purpose"

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
  )
}

export default CreateBooking
