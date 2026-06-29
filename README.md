
# Strategy Pattern - Spring Boot Demo

A Spring Boot 4.1.0 project demonstrating the **Strategy Design Pattern** with a payment processing REST API.

## Tech Stack

- Java 26, Spring Boot 4.1.0, Maven
- `spring-boot-starter-webmvc`

## Project Structure

```
src/main/java/com/mrlabs/strategy/
├── PaymentStrategy.java              # Strategy interface
├── CreditCardPaymentStrategy.java    # Concrete strategy
├── UpiPaymentStrategy.java           # Concrete strategy
├── NetBankingPaymentStrategy.java    # Concrete strategy
├── PaymentFactory.java               # Resolves strategy from Spring bean map
├── PaymentRequest.java               # Request DTO
├── PaymentController.java            # REST controller
└── StrategySpringDemoApplication.java
```

Additional design pattern examples (`com.mrlabs`):
- `Computer.java` — Builder pattern
- `LazySingleton.java` / `MultiThreadSingleton.java` — Singleton pattern

## How It Works

1. `PaymentStrategy` defines the contract: `void pay(PaymentRequest request)`
2. Three concrete strategies (`CreditCardPaymentStrategy`, `UpiPaymentStrategy`, `NetBankingPaymentStrategy`) are registered as Spring beans with names `creditcardPayment`, `upiPayment`, `netbankingPayment`
3. `PaymentFactory` injects `Map<String, PaymentStrategy>` (auto-populated by Spring) and resolves the correct bean by lowercasing the `method` field and appending `"Payment"`
4. `PaymentController` accepts a `POST` request, delegates to the factory, and returns a response

## REST API

### POST `/api/payments/checkout`

Processes a payment using the specified payment method.

#### Request Body

| Field        | Type   | Required | Description                                           |
|-------------|--------|----------|-------------------------------------------------------|
| `amount`     | number | Yes      | Payment amount                                        |
| `method`     | string | Yes      | Payment method — `CREDIT_CARD`, `UPI`, or `NET_BANKING` |
| `cardNumber` | string | For CREDIT_CARD | Card number                                    |
| `cvv`        | string | For CREDIT_CARD | CVV                                            |
| `upiId`      | string | For UPI  | UPI ID (e.g., `user@upi`)                             |
| `bankId`     | string | For NET_BANKING | Bank identifier                                  |

#### Example Requests

**Credit Card:**
```bash
curl -X POST http://localhost:8080/api/payments/checkout \
  -H "Content-Type: application/json" \
  -d '{
    "amount": 150.00,
    "method": "CREDIT_CARD",
    "cardNumber": "4111111111111111",
    "cvv": "123"
  }'
```

**UPI:**
```bash
curl -X POST http://localhost:8080/api/payments/checkout \
  -H "Content-Type: application/json" \
  -d '{
    "amount": 250.00,
    "method": "UPI",
    "upiId": "user@upi"
  }'
```

**Net Banking:**
```bash
curl -X POST http://localhost:8080/api/payments/checkout \
  -H "Content-Type: application/json" \
  -d '{
    "amount": 500.00,
    "method": "NET_BANKING",
    "bankId": "HDFC001"
  }'
```

#### Response

- **200 OK:** `Payment processed successfully using <METHOD>`
- **400 Bad Request:** Error message (e.g., `No strategy found for payment method: INVALID`)

## Build & Run

```bash
# Build
./mvnw clean package

# Run
./mvnw spring-boot:run
```

Server starts on `http://localhost:8080` by default.
=======

