import { defineConfig } from "vite";
import react from "@vitejs/plugin-react";

export default defineConfig({
  plugins: [react()],
  server: {
    port: 5173, // Frontend port
    proxy: {
      "/users": {
        target: "http://localhost:8080", // Backend URL
        changeOrigin: true,
        secure: false,
     },
      "/profiles": {
        target: "http://localhost:8080", // Backend URL
        changeOrigin: true,
        secure: false,
      },
      "/song": {
        target: "http://localhost:8080", // Backend URL
        changeOrigin: true,
        secure: false,
      },
    },
  },
});