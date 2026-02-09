# Production Manager Service

Production management system developed with Spring Boot to control products, raw materials, and their compositions.

## 📋 Table of Contents

- [About the Project](#about-the-project)
- [Technologies Used](#technologies-used)
- [Architecture](#architecture)
- [Prerequisites](#prerequisites)
- [Installation and Setup](#installation-and-setup)
- [Running the Project](#running-the-project)
- [Database Structure](#database-structure)
- [API Endpoints](#api-endpoints)
- [API Documentation](#api-documentation)
- [Project Structure](#project-structure)

## 🎯 About the Project

**Production Manager Service** is a RESTful API developed to manage the production process, allowing:

- **Product Management**: Create, read, update, and delete products
- **Raw Materials Management**: Control raw materials inventory
- **Product Compositions**: Define which raw materials and quantities are needed to produce each product

## 🚀 Technologies Used

### Backend
- **Java 21** - Programming language
- **Spring Boot 4.0.2** - Main framework
- **Spring Data JPA** - Data persistence
- **Spring Web MVC** - REST API
- **PostgreSQL** - Relational database
- **Flyway** - Database versioning and migration
- **MapStruct 1.5.5** - Entity to DTO mapping
- **Lombok** - Boilerplate code reduction
- **SpringDoc OpenAPI 2.3.0** - Automatic API documentation (Swagger)

### Development Tools
- **Maven** - Dependency management
- **Spring Boot DevTools** - Hot reload during development

## 🏗️ Architecture

The project follows a Layered Architecture pattern:

```
┌─────────────────────────────────────┐
│         Controllers                 │  ← Presentation Layer (REST API)
├─────────────────────────────────────┤
│         Services                    │  ← Business Logic Layer
├─────────────────────────────────────┤
│         Repositories                │  ← Data Access Layer
├─────────────────────────────────────┤
│         Entities (Models)           │  ← Domain Layer
└─────────────────────────────────────┘
```

**Design Patterns Used:**
- **DTO (Data Transfer Object)**: Separation between domain entities and transfer objects
- **Repository Pattern**: Data access abstraction
- **Service Layer**: Business logic encapsulation
- **Dependency Injection**: Inversion of control via Spring

## 📦 Prerequisites

Before you begin, you will need to have the following installed on your machine:

- [Java JDK 21](https://www.oracle.com/java/technologies/downloads/#java21) or higher
- [PostgreSQL 12](https://www.postgresql.org/download/) or higher
- [Maven 3.8+](https://maven.apache.org/download.cgi) (or use the included Maven Wrapper)
- [Git](https://git-scm.com/downloads)

## ⚙️ Installation and Setup

### 1. Clone the repository

```bash
git clone <repository-url>
cd production-manager-service
```

### 2. Configure the Database

Create a PostgreSQL database:

```sql
CREATE DATABASE production_manager;
```

### 3. Configure environment variables

Edit the file `src/main/resources/application-dev.properties` with your credentials:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/production_manager
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### 4. Run database migrations

Flyway migrations will run automatically when starting the application. They will create:
- Table `products` (products)
- Table `raw_materials` (raw materials)
- Table `product_compositions` (product compositions)

## 🎮 Running the Project

### Using Maven Wrapper (recommended)

```bash
# Windows
mvnw.cmd spring-boot:run

# Linux/Mac
./mvnw spring-boot:run
```

### Using installed Maven

```bash
mvn spring-boot:run
```

The application will be available at: **http://localhost:8080**

## 🗄️ Database Structure

### Table: `products`
| Column | Type | Description |
|--------|------|-------------|
| id | UUID | Unique identifier (PK) |
| code | INTEGER | Product code (auto-increment) |
| name | VARCHAR(255) | Product name |
| price | DECIMAL(10,2) | Product price |
| created_at | TIMESTAMP | Creation date |
| updated_at | TIMESTAMP | Update date |

### Table: `raw_materials`
| Column | Type | Description |
|--------|------|-------------|
| id | UUID | Unique identifier (PK) |
| code | INTEGER | Raw material code (auto-increment) |
| name | VARCHAR(255) | Raw material name |
| stock_quantity | DECIMAL(10,2) | Stock quantity |
| created_at | TIMESTAMP | Creation date |
| updated_at | TIMESTAMP | Update date |

### Table: `product_compositions`
| Column | Type | Description |
|--------|------|-------------|
| id | UUID | Unique identifier (PK) |
| product_id | UUID | Product reference (FK) |
| material_id | UUID | Raw material reference (FK) |
| quantity_required | DECIMAL(10,2) | Required quantity |
| created_at | TIMESTAMP | Creation date |
| updated_at | TIMESTAMP | Update date |

### Relationships
- A **Product** can have multiple **Compositions** (1:N)
- A **Raw Material** can be in multiple **Compositions** (1:N)
- A **Composition** belongs to one **Product** and one **Raw Material** (N:1)

## 📡 API Endpoints

### Products

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/products` | Create new product |
| GET | `/api/products/{id}` | Get product by ID |
| GET | `/api/products` | List all products |
| GET | `/api/products/ordered-by-price` | List products ordered by price (descending) |
| PUT | `/api/products/{id}` | Update product |
| DELETE | `/api/products/{id}` | Delete product |

**Request Example (POST /api/products):**
```json
{
  "name": "Office Chair",
  "price": 450.00
}
```

**Response Example:**
```json
{
  "id": "123e4567-e89b-12d3-a456-426614174000",
  "code": 1,
  "name": "Office Chair",
  "price": 450.00,
  "materials": []
}
```

### Raw Materials

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/raw-materials` | Create new raw material |
| GET | `/api/raw-materials/{id}` | Get raw material by ID |
| GET | `/api/raw-materials` | List all raw materials |
| PUT | `/api/raw-materials/{id}` | Update raw material |
| DELETE | `/api/raw-materials/{id}` | Delete raw material |

**Request Example (POST /api/raw-materials):**
```json
{
  "name": "MDF Wood",
  "stockQuantity": 150.50
}
```

**Response Example:**
```json
{
  "id": "123e4567-e89b-12d3-a456-426614174001",
  "code": 1,
  "name": "MDF Wood",
  "stockQuantity": 150.50
}
```

### Product Compositions

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/product-compositions` | Create new composition |
| GET | `/api/product-compositions/{id}` | Get composition by ID |
| GET | `/api/product-compositions` | List all compositions |
| PUT | `/api/product-compositions/{id}` | Update composition |
| DELETE | `/api/product-compositions/{id}` | Delete composition |

**Request Example (POST /api/product-compositions):**
```json
{
  "productId": "123e4567-e89b-12d3-a456-426614174000",
  "materialId": "123e4567-e89b-12d3-a456-426614174001",
  "quantityRequired": 2.5
}
```

**Response Example:**
```json
{
  "id": "123e4567-e89b-12d3-a456-426614174002",
  "product": {
    "id": "123e4567-e89b-12d3-a456-426614174000",
    "name": "Office Chair"
  },
  "rawMaterial": {
    "id": "123e4567-e89b-12d3-a456-426614174001",
    "name": "MDF Wood"
  },
  "quantityRequired": 2.5
}
```

## 📚 API Documentation

Interactive API documentation is available through Swagger UI:

**URL:** http://localhost:8081/swagger-ui.html

You can also access the OpenAPI specification in JSON format:

**URL:** http://localhost:8081/api-docs

## 📁 Project Structure

```
production-manager-service/
├── src/
│   ├── main/
│   │   ├── java/com/production/manager/
│   │   │   ├── config/              # Application configurations
│   │   │   ├── controller/          # REST Controllers
│   │   │   │   ├── ProductController.java
│   │   │   │   ├── RawMaterialsController.java
│   │   │   │   └── ProductCompositionsController.java
│   │   │   ├── dto/                 # Data Transfer Objects
│   │   │   │   ├── product/
│   │   │   │   │   ├── requests/
│   │   │   │   │   └── responses/
│   │   │   │   ├── raw_materials/
│   │   │   │   │   ├── requests/
│   │   │   │   │   └── responses/
│   │   │   │   └── product_compositions/
│   │   │   │       ├── requests/
│   │   │   │       └── responses/
│   │   │   ├── mapper/              # MapStruct Mappers
│   │   │   │   ├── ProductMapper.java
│   │   │   │   ├── RawMaterialMapper.java
│   │   │   │   └── ProductCompositionsMapper.java
│   │   │   ├── model/               # JPA Entities
│   │   │   │   ├── Product.java
│   │   │   │   ├── RawMaterials.java
│   │   │   │   └── ProductCompositions.java
│   │   │   ├── repository/          # Spring Data JPA Repositories
│   │   │   │   ├── products/
│   │   │   │   ├── raw_material/
│   │   │   │   └── product_compositions/
│   │   │   ├── service/             # Service Layer
│   │   │   │   ├── products/
│   │   │   │   ├── raw_materials/
│   │   │   │   └── product_compositions/
│   │   │   └── ProductionManagerServiceApplication.java
│   │   └── resources/
│   │       ├── db/migration/        # Flyway Scripts
│   │       │   ├── V1__create_initial_schema.sql
│   │       │   └── V2__add_timestamps_and_rename_codigo_to_code.sql
│   │       ├── application.properties
│   │       └── application-dev.properties
│   └── test/                        # Unit and integration tests
├── pom.xml                          # Maven configuration
└── README.md
```

## 🔧 Additional Configurations

### Profiles

The project uses Spring Profiles for different environments:

- **dev**: Development environment (port 8081, SQL logs enabled)
- **prod**: Production environment (configure as needed)

To change the active profile, edit `application.properties`:

```properties
spring.profiles.active=dev
```

### Flyway

Database migrations are managed by Flyway. Scripts are located in:
```
src/main/resources/db/migration/
```

Naming convention:
- `V1__description.sql` - Versioned migration
- `V2__description.sql` - Next version

### MapStruct

MapStruct is used for mapping between entities and DTOs. Mappers are interfaces annotated with `@Mapper` and the implementation is automatically generated at compile time.

## 🐛 Troubleshooting

### Error: "Port 8081 already in use"

Check if another process is using the port:
```bash
# Windows
netstat -ano | findstr :8081

# Linux/Mac
lsof -i :8081
```

Change the port in `application-dev.properties` if necessary.

### Error: Flyway checksum mismatch

Run the command to repair Flyway:
```bash
mvn flyway:repair
```

### Error: MapStruct - Unmapped target properties

Make sure all fields are mapped or explicitly ignored with `@Mapping(target = "field", ignore = true)`.

## 📝 License

This project is a demonstration project for educational purposes.

## 👥 Author

Developed as part of a production management system.

---

**Note:** Remember not to commit sensitive credentials (passwords, tokens) to the repository. Use environment variables or local configuration files that are not versioned.
