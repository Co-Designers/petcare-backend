# PetCare Platform -- REST API Technical Stories

## Overview

This document adapts the Learning Center Platform REST API guide to the PetCare domain.

Common conventions:

- Base path: `/api/v1`
- JSON request and response bodies use `Content-Type: application/json`
- Swagger UI: `/swagger-ui.html`
- Main roles: `OWNER`, `CLINIC`, `MOBILE`

## TS-IAM001 -- Sign in

As a user, I want to sign in with username and password so that the frontend can redirect me to the correct interface.

Endpoint: `POST /api/v1/authentication/sign-in`

## TS-PET001 -- Manage pets

As a pet owner, I want to register and maintain my pets so that I can schedule veterinary attention.

Endpoints: `GET/POST/PUT/DELETE /api/v1/pets`

## TS-APP001 -- Create appointment

As a pet owner, I want to create an appointment with a clinic or mobile professional.

Endpoint: `POST /api/v1/appointments`

## TS-APP002 -- Get appointments by owner or clinic

As an owner or clinic, I want to list appointments according to my role.

Endpoints:

- `GET /api/v1/appointments?ownerId={ownerId}`
- `GET /api/v1/appointments?clinicId={clinicId}`

## TS-MOB001 -- Manage mobile services

As a mobile veterinary professional, I want to publish services, availability and manage requests.

Endpoints:

- `/api/v1/mobile-professionals`
- `/api/v1/mobile-services`
- `/api/v1/mobile-availability`
- `/api/v1/mobile-requests`
- `/api/v1/mobile-appointments`
