# VenueSync - Role-Based Access Control Implementation

> Documentation for the RBAC security layer implementation

## Problem Summary
The common topbar implementation caused the following issues:
1. **All users could access ticket booking**: Organizers and Staff could see and book tickets, which should only be available to Attendees
2. **Staff validation not working**: Staff members were seeing the tickets page instead of the QR validation page
3. **No role-based restrictions**: The backend API endpoints were not properly restricted by user roles

## Root Cause
1. The NavBar component displayed "Tickets" link for ALL authenticated users
2. Backend endpoints (`/api/v1/tickets/**` and ticket purchase) had no role restrictions
3. Frontend routes had no role-based protection, only authentication checks

## Solution Implemented

### 1. Backend Changes (SecurityConfig.java)
Added role-based authorization for API endpoints:
- `/api/v1/tickets/**` - Restricted to `ROLE_ATTENDEE` only
- `/api/v1/events/*/ticket-types/*/tickets` - Restricted to `ROLE_ATTENDEE` only (ticket purchase)
- `/api/v1/events` - Restricted to `ROLE_ORGANIZER` (already existed)
- `/api/v1/ticket-validations` - Restricted to `ROLE_STAFF` (already existed)

### 2. Frontend NavBar Changes (nav-bar.tsx)
Updated navigation links to show role-specific options:
- **Organizers**: See "Events" link
- **Attendees**: See "Tickets" link
- **Staff**: See "Validate QR" link

### 3. Frontend Route Protection
Created new `RoleProtectedRoute` component that:
- Checks if user is authenticated
- Validates user has required role(s)
- Redirects to `/dashboard` if user lacks required role
- Dashboard then redirects to appropriate page based on actual role

Applied role-based protection to routes:
- `/events/:eventId/purchase/:ticketTypeId` - Requires `ATTENDEE` role
- `/dashboard/events` - Requires `ORGANIZER` role
- `/dashboard/events/create` - Requires `ORGANIZER` role
- `/dashboard/events/update/:id` - Requires `ORGANIZER` role
- `/dashboard/tickets` - Requires `ATTENDEE` role
- `/dashboard/tickets/:id` - Requires `ATTENDEE` role
- `/dashboard/validate-qr` - Requires `STAFF` role

### 4. Dashboard Routing (dashboard-page.tsx)
Already correctly implemented:
- Organizers → `/dashboard/events`
- Staff → `/dashboard/validate-qr`
- Attendees → `/dashboard/tickets`

## Files Modified

### Backend
- `backend/src/main/java/com/fullstack/tickets/config/SecurityConfig.java`

### Frontend
- `frontend/src/components/nav-bar.tsx` - Updated to show role-specific links
- `frontend/src/components/role-protected-route.tsx` - **NEW FILE** - Role-based route protection
- `frontend/src/main.tsx` - Updated routes to use `RoleProtectedRoute`

## Testing Checklist

### As Attendee
- ✓ Can see "Tickets" link in navbar
- ✓ Can access `/dashboard/tickets`
- ✓ Can purchase tickets from published events
- ✓ Cannot access `/dashboard/events`
- ✓ Cannot access `/dashboard/validate-qr`

### As Organizer
- ✓ Can see "Events" link in navbar
- ✓ Can access `/dashboard/events`
- ✓ Can create/edit events
- ✓ Cannot access `/dashboard/tickets`
- ✓ Cannot purchase tickets
- ✓ Cannot access `/dashboard/validate-qr`

### As Staff
- ✓ Can see "Validate QR" link in navbar
- ✓ Can access `/dashboard/validate-qr`
- ✓ Can validate tickets
- ✓ Cannot access `/dashboard/events`
- ✓ Cannot access `/dashboard/tickets`
- ✓ Cannot purchase tickets

## Security Layers
The implementation provides multiple layers of security:
1. **Backend Authorization**: Spring Security role checks on API endpoints
2. **Frontend Route Guards**: `RoleProtectedRoute` component prevents unauthorized route access
3. **UI Visibility**: NavBar only shows links for authorized actions
4. **Automatic Redirection**: Dashboard page redirects users to their appropriate landing page

## Additional Notes
- Backend security is the primary enforcement mechanism
- Frontend protections improve UX and prevent unnecessary API calls
- All changes are backward compatible with existing authentication flow
- No changes required to user provisioning or JWT token generation

