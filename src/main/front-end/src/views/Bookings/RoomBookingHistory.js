import React, { useState, useEffect } from 'react'
import {
  CCard,
  CCardBody,
  CCardHeader,
  CCol,
  CRow,
  CDataTable,
} from '@coreui/react'
import { DocsLink } from 'src/reusable'
import { Formik, Form, useField } from 'formik';
import * as Yup from 'yup';
import axios from 'axios'


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
const ROOMS_BOOKING_API_BASE_URL = "http://localhost:9000/apis/v1/bookings/rooms";
const ROOMS_API_BASE_URL = "http://localhost:9000/apis/v1/rooms";

const bookings_fields = ['venue_date','status', 'time_from', 'time_to','club','created_at']

const RoomBookingHistory = () => {
  const [rooms, setRooms] = useState([]);
  const [bookings, setBookings] = useState({ data_list: [] });
  const [selectedRoom, setSelectedRoom] = useState();

  useEffect(() => {
    axios.get(ROOMS_API_BASE_URL)
      .then(res => {
        console.log(res.data)
        setRooms(res.data.data_list[0])
      })
      .catch(err => {
      })

    axios.get(ROOMS_BOOKING_API_BASE_URL + '/' + selectedRoom + '/history')
      .then(res => {
        console.log(res.data)
        setBookings(res.data)
      })
      .catch(err => {

      })
  }, [selectedRoom])

  if (!rooms) {
    return <h1>Buildings not found</h1>
  }
  return (
    <CRow>
      <CCol md="4">
        <CCard>
          <CCardHeader>
            Select Room
            <DocsLink name="CCallout" />
          </CCardHeader>

          <CCardBody>
            <Formik
              initialValues={{
                room_id: ''
              }}
              validationSchema={Yup.object({
                room_id: Yup.string()
                  .max(20, 'Must be 20 characters or less')
                  .required('Required'),
              })}

              onSubmit={(values, { setSubmitting }) => {
                setSelectedRoom(values.room_id)

              }}
            >
              <Form>
                <CRow className="justify-content-center">
                  <CCol md="6">
                    <MySelect label="Room" name="room_id">
                      {rooms.map((c) =>
                        <option key={c.id} value={c.id} type="number">{c.name} </option>
                      )}
                    </MySelect>
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

      </CCol>
      <CCol md={8}>

        <CCard>
          <CCardHeader>
            Bookings History
          </CCardHeader>
          <CCardBody>
            <CDataTable
              items={bookings.data_list[0]}
              fields={bookings_fields}
              itemsPerPage={5}
              pagination
            scopedSlots={{
              'club':
                (item) => (
                  <td>
                    {item.club.name}
                  </td>
                )
                

            }}

            />
          </CCardBody>
        </CCard>
      </CCol>
    </CRow>
  )
}

export default RoomBookingHistory
