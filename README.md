

[![Code Size](https://img.shields.io/github/languages/code-size/karthikbanjan/Code-Sharing-Platform?label=Code%20Size)]() 
[![Java](https://img.shields.io/github/languages/top/karthikbanjan/Code-Sharing-Platform)]()


# Code Sharing Platform

A backend server with basic view capabalities. REST API for sharing codes 
with or without restrictions.



## API Reference

#### Publishing new code

```http
  POST /api/code/new
```

| Body | Type | Description |
|------|------|-------------|
| `code, time, views` | `JSON` | Accepts a new code snippet as a request body and saves it. Returns the UUID of the saved code snippet in the response body. View is the limit on the number of views. Time is the limit on the viewing time in seconds. | 


#### Retrieving Code By ID

```http
  GET /api/code/{uuid}
```

| Path Variable | Description |
| ------------- | ----------- | 
| `id` | Accepts a UUID as a path variable and returns the corresponding code snippet if it exists. Returns a 404 NOT FOUND error if the code snippet is not found or has expired. |


#### Retrieving Latest Codes

```http
  GET /api/code/latest
```

| Description |
| ----------- |
| Returns a list of the latest code snippets that are without restrictions. Returns a 404 NOT FOUND error if no code snippets are found. |

## Tech Stack

**View:** HTML, JavaScript and Apache FreeMarker

**Server:** Java (Spring Boot)


## Lessons Learned

- Creating models for all required data.
- Creating repository for all required data.
- Storing relevant data in a database.
- Retreive data from database using id/paging.
- Directing GET/POST/DELETE requests.
- Using Apache FreeMarker Template.
- Building projects with gradle.





## Acknowledgements

 - Hyperskill for the awesome in-depth literature on languages and project outlines.
 - Katherine Oelsner for the handy readme helper.

