import axiosInstance from "./axios-instance";

import { Pet } from "../types";

const API_URL = "/pets";

// Fetch all pets
export const fetchPets = async (): Promise<Pet[]> => {
  const response = await axiosInstance.get<Pet[]>(API_URL);
  return response.data;
};

// Fetch a single pet
export const getPetByIdFromAPI = async (id: string): Promise<Pet> => {
  const response = await axiosInstance.get<Pet>(`${API_URL}/${id}`);
  return response.data;
};

// Add a new pet
export const addNewPet = async (pet: Omit<Pet, "id">): Promise<Pet> => {
  const response = await axiosInstance.post<Pet>(API_URL, pet);
  return response.data;
};

// Update an existing pet
export const updateApiPet = async (
  id: string,
  updatedPet: Partial<Pet>
): Promise<Pet> => {
  const response = await axiosInstance.put<Pet>(`${API_URL}/${id}`, updatedPet);
  return response.data;
};

// Delete a pet
export const deleteApiPet = async (id: string): Promise<void> => {
  await axiosInstance.delete(`${API_URL}/${id}`);
};

// get pets by guardian id
export const getPetsByGuardianId = async (id: string): Promise<Pet[]> => {
  const response = await axiosInstance.get<Pet[]>(
    `${API_URL}?guardianId=${id}`
  );
  return response.data;
};
