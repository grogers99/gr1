# System Patterns: ToDo Application

## Architecture Overview

The ToDo application follows a standard Spring Boot MVC architecture with a layered design:

```
┌─────────────┐     ┌─────────────┐     ┌─────────────┐     ┌─────────────┐
│   Browser   │────▶│  Controller │────▶│  Repository │────▶│   Database  │
│  (Frontend) │◀────│   (REST)    │◀────│   (JPA)     │◀────│ (PostgreSQL)│
└─────────────┘     └─────────────┘     └─────────────┘     └─────────────┘
```

## Key Components

### Backend Components

#### Model Layer
- **Task Entity**: Represents a task with id, description, and status
- **TaskStatus Enum**: Defines possible task states (OPEN, IN_PROGRESS, COMPLETED, CANCELLED)

#### Repository Layer
- **TaskRepository**: JPA repository interface that extends JpaRepository for CRUD operations on Task entities

#### Controller Layer
- **TaskController**: REST controller that exposes endpoints for task operations

### Frontend Components
- **HTML/CSS/JavaScript**: Single-page application that communicates with the backend via REST API

## Design Patterns

### Repository Pattern
- Used for data access abstraction
- JpaRepository provides standard CRUD operations
- No custom query methods currently implemented

### MVC Pattern
- **Model**: Task entity and TaskStatus enum
- **View**: HTML/CSS/JavaScript frontend
- **Controller**: TaskController handling HTTP requests

### REST API Pattern
- Stateless communication between frontend and backend
- HTTP methods mapped to CRUD operations:
  - PUT: Create a task
  - GET: Retrieve tasks
  - POST: Update task status
  - DELETE: Remove a task

## Data Flow

### Task Creation
1. User enters task description in the frontend
2. Frontend sends PUT request to `/tasks/add` with description parameter
3. Controller creates a new Task object with OPEN status
4. Repository saves the Task to the database
5. Controller returns the saved Task
6. Frontend refreshes the task list

### Task Completion
1. User clicks "Complete" button for a task
2. Frontend sends POST request to `/tasks/complete/{id}`
3. Controller retrieves the Task by id
4. Controller updates the Task status to COMPLETED
5. Repository saves the updated Task
6. Controller returns the updated Task
7. Frontend refreshes the task list

### Task Deletion
1. User clicks "Delete" button for a task
2. Frontend sends DELETE request to `/tasks/delete/{id}`
3. Controller calls repository to delete the Task by id
4. Frontend refreshes the task list

## Component Relationships

- **Task ↔ TaskStatus**: One-to-one relationship (each Task has one TaskStatus)
- **TaskController ↔ TaskRepository**: Controller depends on Repository for data operations
- **Frontend ↔ TaskController**: Frontend communicates with Controller via REST API

## Critical Implementation Paths

### Database Schema
- Task table with columns: id, description, status
- Status stored as a string (enum name)

### API Endpoints
- `PUT /tasks/add`: Creates a new task
- `GET /tasks/show`: Retrieves all tasks
- `POST /tasks/complete/{id}`: Marks a task as completed
- `DELETE /tasks/delete/{id}`: Deletes a task

### Frontend-Backend Integration
- Frontend makes asynchronous requests to the backend API
- JSON data format for communication
- Frontend handles rendering based on task status
