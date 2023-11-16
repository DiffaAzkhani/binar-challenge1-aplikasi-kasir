# Tugas Challenge 5 (API) Binar Fud

## Swagger

Run Project > buka browser > http://localhost:8080/swagger-ui/index.html

## POSTMAN

Run Project > buka Postman > Buat Request (Format JSON) > Send

## Fitur

### A. Merchant
- Tambah Merchant
  URL Endpoint (POST)   : http://localhost:8080/api/merchants/add-merchant

<pre>
{
  "merchantId": 0,
  "merchantName": "string",
  "merchantLocation": "string",
  "open": true
}
</pre>

- Hapus Merchant
  URL Endpoint (DELETE) : http://localhost:8080/api/merchants/delete-merchant/{merchantId}

<pre>
Masukkan parameter "merchantId" : 0
</pre>

- Edit Merchant
  URL Endpoint (PUT)    : http://localhost:8080/api/merchants/update-merchant

<pre>
{
  "merchantId": 0,
  "merchantName": "string",
  "merchantLocation": "string",
  "open": true
}
</pre>

- Ambil semua merchant
  URL Endpoint (GET)    : http://localhost:8080/api/merchants/list-merchant

<pre>
Tanpa parameter apapun untuk mendapatkan list semua merchant
</pre>

- Ambil semua merchant (Buka)
  URL Endpoint (GET)    : http://localhost:8080/api/merchants/list-open-merchants

<pre>
Tanpa parameter apapun untuk mendapatkan list semua merchant
</pre>

### B. Order
- Tambah Pesanan
  URL Endpoint (POST)   : http://localhost:8080/api/orders/create-order

<pre>
{
  "userId": 0,
  "productId": 0,
  "quantity": 0,
  "destinationAddress": "string"
}
</pre>

- Mengambil order berdasarkan id user
  URL Endpoint (GET)    : http://localhost:8080/api/orders/list-order/{userId}

<pre>
Masukkan parameter "userId" : 0
</pre>

### C. Product
- Tambah Product
  URL Endpoint (POST)   : http://localhost:8080/api/products/add-product

<pre>
{
  "productId": 0,
  "productName": "string",
  "price": 0,
  "merchant": {
    "merchantId": 0,
    "merchantName": "string",
    "merchantLocation": "string",
    "open": true
  }
}
</pre>

- Hapus Product
  URL Endpoint (DELETE) : http://localhost:8080/api/products/delete-product/{productId}

<pre>
Masukkan parameter "productId" : 0
</pre>
- Update Product
  URL Endpoint (PUT)    : http://localhost:8080/api/products/update-product

<pre>
{
  "productId": 0,
  "productName": "string",
  "price": 0,
  "merchant": {
    "merchantId": 0,
    "merchantName": "string",
    "merchantLocation": "string",
    "open": true
  }
}
</pre>

- Ambil product berdasarkan id merchant
  URL Endpoint (GET)    : http://localhost:8080/api/products/get-products/{merchantId}

<pre>
Masukkan parameter "merchantId : 0"
</pre>

### D. Users
- Register
  URL Endpoint (POST)   : http://localhost:8080/api/users/register

<pre>
{
  "userId": 0,
  "username": "string",
  "emailAddress": "string",
  "password": "string",
  "role": "ADMIN"
}
</pre>

- Login
  URL Endpoint (POST)   : http://localhost:8080/api/users/login

<pre>
{
  "userId": 0,
  "username": "string",
  "emailAddress": "string",
  "password": "string",
  "role": "ADMIN"
}
</pre>

- Update
  URL Endpoint (PUT)    : http://localhost:8080/api/users/update-user

<pre>
{
  "userId": 0,
  "username": "string",
  "emailAddress": "string",
  "password": "string",
  "role": "ADMIN"
}
</pre>
- Delete
  URL Endpoint (DELETE) : http://localhost:8080/api/users/delete-user/{userId}

<pre>
Masukkan parameter "userId" : 0
</pre>

**Maaf implementasi Jasper untuk cetak nota pembayaran (PDF) tidak ada -> Skill Issue :)**