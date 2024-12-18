import axiosInstance from "./axios-instance";

import { Appointment } from "../types";

export const getNextAppointmentByPetId = async (id: string) => {
  const response = await axiosInstance.get<Appointment[]>(
    "/appointments/next?petId=" + id
  );
  return response.data;
};

export const getPastAppointmentsByPetId = async (
  id: string
): Promise<Appointment[]> => {
  const response = await axiosInstance.get<Appointment[]>(
    "/appointments/past?petId=" + id
  );
  return response.data;
};

export const createAppointment = async (appointment: Appointment) => {
  console.log("Appontment", appointment);
  const response = await axiosInstance.post<Appointment>(
    "/appointments",
    appointment
  );
  return response.data;
};
