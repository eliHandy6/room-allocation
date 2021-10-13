import React from 'react'
import CIcon from '@coreui/icons-react'

const _nav =  [
  {
    _tag: 'CSidebarNavItem',
    name: 'Chuka University',
    to: '/dashboard',
    icon: <CIcon name="cil-speedometer" customClasses="c-sidebar-nav-icon"/>,
   
  },
  {
    _tag: 'CSidebarNavTitle',
    _children: ['Theme']
  },
  {
    _tag: 'CSidebarNavItem',
    name: 'Rooms',
    to: '/rooms/view',
    icon: 'cil-drop',
  },
  {
    _tag: 'CSidebarNavItem',
    name: 'Bookings',
    to: '/bookings/view',
    icon: 'cil-pencil',
  },
  {
    _tag: 'CSidebarNavTitle',
    _children: ['Components']
  },
  {
    _tag: 'CSidebarNavDropdown',
    name: 'Buildings',
    route: '/buildings',
    icon: 'cil-puzzle',
    _children: [
      {
        _tag: 'CSidebarNavItem',
        name: 'Register Building',
        to: '/buildings/create',
      },
      {
        _tag: 'CSidebarNavItem',
        name: 'View Buildings',
        to: '/buildings/view',
      },

    ],
  },
  {
    _tag: 'CSidebarNavDropdown',
    name: 'Rooms',
    route: '/rooms',
    icon: 'cil-cursor',
    _children: [
      {
        _tag: 'CSidebarNavItem',
        name: 'Register Room',
        to: '/rooms/create',
      },
      {
        _tag: 'CSidebarNavItem',
        name: 'View Rooms',
        to: '/rooms/view',
      },

      {
        _tag: 'CSidebarNavItem',
        name: 'View Rooms in a Building',
        to: '/rooms/inabuilding',
      }
    ],
  },

  {
    _tag: 'CSidebarNavDropdown',
    name: 'Clubs',
    route: '/clubs',
    icon: 'cil-star',
    _children: [
      {
        _tag: 'CSidebarNavItem',
        name: 'Register a club',
        to: '/clubs/create',
        badge: {
          color: 'success',
          text: 'NEW',
        },
      },
      {
        _tag: 'CSidebarNavItem',
        name: 'View Clubs',
        to: '/clubs/view',
      },

    ],
  },
  {
    _tag: 'CSidebarNavDropdown',
    name: 'Bookings',
    route: '/bookings',
    icon: 'cil-bell',
    _children: [
      {
        _tag: 'CSidebarNavItem',
        name: 'Book a room',
        to: '/bookings/create',
      },
      {
        _tag: 'CSidebarNavItem',
        name: 'Bookings',
        to: '/bookings/view',
      },
      {
        _tag: 'CSidebarNavItem',
        name: 'Room Booking History',
        to: '/bookings/room-history',
      },
  
  
     
    ]
  }
 
]

export default _nav
