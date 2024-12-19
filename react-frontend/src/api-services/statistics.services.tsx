import axiosInstance from "./axios-instance";

const API_URL = "/statistics";

// Fetch all pets
export const statisticsGlobal = async (): Promise<unknown> => {
  const response = await axiosInstance.get<unknown>(API_URL + "/global");
  return response.data;
};
