import { useEffect, useState } from 'react';
import { Pet } from '../types';
import { mockPets } from '../data/mockData';
import { fetchPets, addNewPet, deleteApiPet, updateApiPet, getPetByIdFromAPI } from '../api-services/pets.services';

export const usePets = () => {
  const [pets, setPets] = useState<Pet[]>(mockPets);
  const [error, setError] = useState<string | null>(null);
 const fetchData = async () => {

      try {
        const data = await fetchPets();
        console.log("pets", data);
        setPets(data);
      } catch (err) {
        setError(err.message);
      
      }
    }
  useEffect(() => {
   
    fetchData();
  }, []);

  const addPet = async (pet: Omit<Pet, 'id'>) => {
    await addNewPet(pet)
    fetchData();

  };

  const updatePet = async (id: string, updatedPet: Partial<Pet>) => {
    console.log("updatePet", id, updatedPet);
    const updated = await updateApiPet(id, updatedPet);
    setPets(pets.map(pet => (pet.id === id ? updated : pet)));
    
  };

  const deletePet = (id: string) => {
    setPets(pets.filter(pet => pet.id !== id));
    deleteApiPet(id);
  };

  const getPetById = async (id: string) => {
    const selectedPet = await getPetByIdFromAPI(id);
    return selectedPet;
  };

  return {
    pets,
    addPet,
    updatePet,
    deletePet,
    getPetById,
  };
};