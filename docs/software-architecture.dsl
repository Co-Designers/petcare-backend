workspace "PetCare Platform" "REST API backend for PetCare" {
    model {
        user = person "Pet owner / Clinic / Mobile professional"
        frontend = softwareSystem "PetCare Frontend" "Angular frontend"
        backend = softwareSystem "PetCare Platform API" "Spring Boot REST API" {
            iam = container "IAM Context" "Authentication and users" "Spring Boot"
            pet = container "Pet Management Context" "Pets" "Spring Boot"
            clinic = container "Clinic Management Context" "Clinics, veterinarians, services, patients" "Spring Boot"
            appointments = container "Appointment Management Context" "Appointments" "Spring Boot"
            mobile = container "Mobile Services Context" "Mobile professionals and requests" "Spring Boot"
            database = container "MySQL Database" "Persistent data" "MySQL"
        }
        user -> frontend "Uses"
        frontend -> backend "Consumes REST API"
        backend -> database "Reads and writes"
    }
}
