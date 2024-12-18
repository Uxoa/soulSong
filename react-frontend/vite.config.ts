import { defineConfig } from "vite";
import react from "@vitejs/plugin-react";

export default defineConfig({
  plugins: [react()],
  server: {
    port: 5173, // Puerto del frontend
    proxy: {
      "/users": {
        target: "http://localhost:8080", // URL del backend Spring Boot
        changeOrigin: true,
        secure: false,
      },
    },
  },
});
