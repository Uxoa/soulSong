import React from "react";
import { usePets } from "../../hooks/usePets";
import { useGuardians } from "../../hooks/useUser.ts";
import PetList from "./PetList";

const PetManager: React.FC = () => {
  const { pets, addPet, updatePet, deletePet } = usePets();
  const { guardians, getGuardiansListByName } = useGuardians();

  return (
    <div className="flex-1 p-8">
      <PetList
        pets={pets}
        guardians={guardians}
        onAdd={addPet}
        onUpdate={updatePet}
        onDelete={deletePet}
        onfindGuardian={getGuardiansListByName}
      />
    </div>
  );
};

export default PetManager;
