import React from 'react';

const Toaster = React.lazy(() => import('./views/notifications/toaster/Toaster'));
const Tables = React.lazy(() => import('./views/base/tables/Tables'));

const CreateBuilding = React.lazy(() => import('./views/Buildings/CreateBuilding'));
const Cards = React.lazy(() => import('./views/base/cards/Cards'));
const ViewBuildings = React.lazy(() => import('./views/Buildings/ViewBuildings'));
const Collapses = React.lazy(() => import('./views/base/collapses/Collapses'));
const BasicForms = React.lazy(() => import('./views/base/forms/BasicForms'));

const Jumbotrons = React.lazy(() => import('./views/base/jumbotrons/Jumbotrons'));
const ListGroups = React.lazy(() => import('./views/base/list-groups/ListGroups'));
const Navbars = React.lazy(() => import('./views/base/navbars/Navbars'));
const Navs = React.lazy(() => import('./views/base/navs/Navs'));
const Paginations = React.lazy(() => import('./views/base/paginations/Pagnations'));
const Popovers = React.lazy(() => import('./views/base/popovers/Popovers'));
const ProgressBar = React.lazy(() => import('./views/base/progress-bar/ProgressBar'));
const Switches = React.lazy(() => import('./views/base/switches/Switches'));

const Tabs = React.lazy(() => import('./views/base/tabs/Tabs'));
const Tooltips = React.lazy(() => import('./views/base/tooltips/Tooltips'));
const BrandButtons = React.lazy(() => import('./views/buttons/brand-buttons/BrandButtons'));
const ViewRooms = React.lazy(() => import('./views/rooms/ViewRooms'));
const RoomsInABuilding = React.lazy(() => import('./views/rooms/ViewRoomsInABuilding'));
const CreateRoom = React.lazy(() => import('./views/rooms/CreateRoom'));
const Charts = React.lazy(() => import('./views/charts/Charts'));
const Dashboard = React.lazy(() => import('./views/dashboard/Dashboard'));
const RegisterClub = React.lazy(() => import('./views/Clubs/CreateClub'));
const ViewClubs = React.lazy(() => import('./views/Clubs/ViewClubs'));
const Brands = React.lazy(() => import('./views/icons/brands/Brands'));
const Bookings = React.lazy(() => import('./views/Bookings/CreateBooking'));
const RoomBookingHistory = React.lazy(() => import('./views/Bookings/RoomBookingHistory'));
const ViewBookings = React.lazy(() => import('./views/Bookings/ViewBookings'));
const Colors = React.lazy(() => import('./views/theme/colors/Colors'));
const Typography = React.lazy(() => import('./views/theme/typography/Typography'));
const Widgets = React.lazy(() => import('./views/widgets/Widgets'));
const Users = React.lazy(() => import('./views/users/Users'));
const User = React.lazy(() => import('./views/users/User'));

const routes = [
  { path: '/', exact: true, name: 'Home' },
  { path: '/dashboard', name: 'Dashboard', component: Dashboard },
  { path: '/theme', name: 'Theme', component: Colors, exact: true },
  { path: '/theme/colors', name: 'Colors', component: Colors },
  { path: '/theme/typography', name: 'Typography', component: Typography },
  { path: '/base', name: 'Base', component: Cards, exact: true },
  { path: '/buildings/create', name: 'RegisterBuilding', component: CreateBuilding },
  { path: '/base/cards', name: 'Cards', component: Cards },
  { path: '/buildings/view', name: 'Buildings', component: ViewBuildings },
  { path: '/base/collapses', name: 'Collapse', component: Collapses },
  { path: '/base/forms', name: 'Forms', component: BasicForms },
  { path: '/base/jumbotrons', name: 'Jumbotrons', component: Jumbotrons },
  { path: '/base/list-groups', name: 'List Groups', component: ListGroups },
  { path: '/base/navbars', name: 'Navbars', component: Navbars },
  { path: '/base/navs', name: 'Navs', component: Navs },
  { path: '/base/paginations', name: 'Paginations', component: Paginations },
  { path: '/base/popovers', name: 'Popovers', component: Popovers },
  { path: '/base/progress-bar', name: 'Progress Bar', component: ProgressBar },
  { path: '/base/switches', name: 'Switches', component: Switches },
  { path: '/base/tables', name: 'Tables', component: Tables },
  { path: '/base/tabs', name: 'Tabs', component: Tabs },
  { path: '/base/tooltips', name: 'Tooltips', component: Tooltips },
  { path: '/buttons', name: 'Buttons', component: CreateRoom, exact: true },
  { path: '/rooms/create', name: 'RegisterRoom', component: CreateRoom },
  { path: '/rooms/view', name: 'All Rooms', component: ViewRooms },
  { path: '/rooms/inabuilding', name: 'Rooms In a Building', component: RoomsInABuilding },
  { path: '/buttons/brand-buttons', name: 'Brand Buttons', component: BrandButtons },
  { path: '/charts', name: 'Charts', component: Charts },
  { path: '/icons', exact: true, name: 'Icons', component: RegisterClub },
  { path: '/clubs/create', name: 'Register Club', component: RegisterClub },
  { path: '/clubs/view', name: 'View Clubs', component: ViewClubs },
  { path: '/icons/brands', name: 'Brands', component: Brands },
  { path: '/bookings', name: 'Bookings', component: Bookings, exact: true },
  { path: '/bookings/create', name: 'Bookings', component: Bookings },
  { path: '/bookings/room-history', name: 'Room Booking History', component: RoomBookingHistory },
  { path: '/bookings/view', name: 'All Bookings', component: ViewBookings },
  { path: '/notifications/toaster', name: 'Toaster', component: Toaster },
  { path: '/widgets', name: 'Widgets', component: Widgets },
  { path: '/users', exact: true,  name: 'Users', component: Users },
  { path: '/users/:id', exact: true, name: 'User Details', component: User }
];

export default routes;
