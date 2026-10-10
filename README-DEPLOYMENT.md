# Smart Library Management System — Deployment

## What this project contains
- `frontend/`: static HTML/CSS/JavaScript website
- `backend/`: Java 17 + Spring Boot REST API

## Deploy backend (Render)
1. Push this repository to GitHub.
2. In Render, choose **New → Blueprint** and connect the repository; Render reads `render.yaml`.
   Alternatively create a Web Service with root directory `ewp/backend`, Docker runtime, and use its Dockerfile.
3. After deployment, copy the service URL, e.g. `https://your-service.onrender.com`.
4. Test `https://your-service.onrender.com/api/books`.

## Deploy frontend (GitHub Pages)
1. In `frontend/config.js`, set `API_BASE_URL` to `https://your-service.onrender.com/api` (keep `/api`).
2. Push the repository to GitHub.
3. In repository **Settings → Pages**, select the branch and `/ewp/frontend` folder if GitHub Pages offers it. If not, put the frontend files in the repository root or deploy `frontend/` with Netlify.
4. Open the generated Pages URL.

## Important notes
- GitHub repository URLs are not live deployment URLs; the backend must be deployed separately.
- The backend currently uses an in-memory H2 database, so records may reset when the service restarts. Use a persistent database for production.
- CORS is currently enabled broadly by the existing controllers. Restrict it to your frontend domain before production use.
- Replace the placeholder backend URL in `frontend/config.js` before using the deployed frontend.
