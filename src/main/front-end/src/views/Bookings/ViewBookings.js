import React, { useState, useEffect } from 'react'
import {
  CBadge,
  CCard,
  CCardBody,
  CCardHeader,
  CCol,
  CDataTable,
  CRow
} from '@coreui/react'
import { DocsLink } from 'src/reusable';
import axios from 'axios';
import { cilReload } from '@coreui/icons';

const BOOKING_API_BASE_URL = "http://localhost:9000/apis/v1/bookings";
const bookings_fields = ['room', 'venue_date', 'status', 'time_from', 'time_to', 'club', 'created_at', 'action']


const ViewBookings = () => {

  const ConfirmRoom = (id) => {
    axios.put(BOOKING_API_BASE_URL + '/' + id + '/confirm')
      .then(res => {
        if (res.status == 200) {
          alert("Confirmed")
          window. location. reload() 
        }
      })
      .catch(err => {
      })

}

const CancelRoom = (id) => {
    axios.put(BOOKING_API_BASE_URL + '/' + id + '/cancel')
      .then(res => {
        if (res.status == 200) {
          alert("Cancelled")
          window. location. reload()
        }
      })
      .catch(err => {
      })
}

  const [bookings, setBookings] = useState({ data_list: [] });
  useEffect(() => {
    axios.get(BOOKING_API_BASE_URL)
      .then(res => {
        console.log(res.data)
        setBookings(res.data)

      })
      .catch(err => {

      })
  }, [])

  const [activeIndex] = useState(1)
  return (
    <>
      <CRow className="justify-content-center">
        <CCol xs="12" lg="12">
          <CCard>
            <CCardHeader>
              Bookings
              <DocsLink name="CModal" />
            </CCardHeader>
            <CCardBody>
              <CDataTable
                items={bookings.data_list[0]}
                fields={bookings_fields}
                itemsPerPage={5}
                pagination
                scopedSlots={{
                  'room':
                    (item) => (
                      <td>
                        {item.room.name}
                      </td>
                    ),
                  'club':
                    (item) => (
                      <td>
                        {item.club.name}
                      </td>
                    ),
                  'action':
                    (item) => (
                      <>
                        <td>
                          <button onClick={() => ConfirmRoom(item.id)}>Confirm</button>
                        </td>
                        <td>
                          <button onClick={() => CancelRoom(item.id)}>Cancel</button>
                        </td>
                      </>
                    )

                }}



              />
            </CCardBody>
          </CCard>
        </CCol>

      </CRow>


    </>
  )
}
export default ViewBookings
