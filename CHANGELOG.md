# Changelog

All notable changes to VenueSync will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [1.0.0] - 2024-12-09

### 🎉 Initial Release

#### Core Features
- **Event Management**
  - Create, update, and delete events with flexible scheduling
  - Multiple ticket types per event with custom pricing
  - Event lifecycle management (Draft → Published → Cancelled → Completed)
  - Sales period configuration with automatic enforcement

- **Ticketing System**
  - Race-condition-proof ticket purchasing using JPA pessimistic locking
  - Atomic seat allocation preventing overselling during concurrent purchases
  - Automatic QR code generation for each ticket (Google ZXing)
  - Digital ticket wallet for attendees

- **QR Validation**
  - Real-time QR code scanning for ticket validation
  - Duplicate usage prevention (ticket marked as USED after first scan)
  - Validation status feedback (VALID, INVALID, EXPIRED, ALREADY_USED)
  - Manual ticket ID entry as fallback

- **Authentication & Authorization**
  - OAuth2/OIDC integration with Keycloak
  - Role-based access control (ORGANIZER, ATTENDEE, STAFF)
  - JWT-based stateless authentication
  - Protected routes on frontend with role verification

#### Technical Implementation

##### Backend
- Spring Boot 3.4.4 REST API
- Spring Security OAuth2 Resource Server
- Spring Data JPA with PostgreSQL
- Pessimistic locking for concurrent purchase handling
- MapStruct for DTO mapping
- Bean Validation for request validation
- Global exception handling with custom error responses

##### Frontend
- React 19 with TypeScript
- Vite build tool for fast development
- Tailwind CSS with custom design system
- Framer Motion animations
- Radix UI accessible components
- react-oidc-context for authentication
- Responsive design (mobile-first approach)

##### Infrastructure
- Docker Compose for local development
- PostgreSQL database
- Keycloak identity management
- CORS configuration for frontend-backend communication

#### Security Features
- JWT token validation on all protected endpoints
- Role-based endpoint protection
- Pessimistic database locking for data integrity
- Input validation and sanitization

---

## [Unreleased]

### Planned Features
- Email notifications for ticket purchases
- Event analytics dashboard for organizers
- Refund processing workflow
- Multi-language support
- Dark/Light theme toggle
- PWA support for mobile devices

---

## Version History

| Version | Date | Description |
|---------|------|-------------|
| 1.0.0 | 2024-12-09 | Initial release with core ticketing features |
