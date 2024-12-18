import React, { useState } from "react";
import { Pet, User } from "../../types";
import { Edit2, Trash2, PlusCircle, Eye } from "lucide-react";
import { PetForm } from "./PetForm";
import { Link } from "react-router-dom";

interface PetListProps {
  pets: Pet[];
  guardians: User[];
  onAdd: (pet: Omit<Pet, "id">) => void;
  onUpdate: (id: string, pet: Partial<Pet>) => void;
  onDelete: (id: string) => void;
  onfindGuardian: (name: string) => void;
}

const PetList: React.FC<PetListProps> = ({
  pets,
  guardians,
  onAdd,
  onUpdate,
  onDelete,
  onfindGuardian,
}) => {
  const [showForm, setShowForm] = useState(false);
  const [editingPet, setEditingPet] = useState<Pet | null>(null);

  const handleEdit = (pet: Pet) => {
    setEditingPet(pet);
    setShowForm(true);
  };

  const handleSubmit = (petData: Pet) => {
    if (editingPet) {
      onUpdate(editingPet.id, petData);
    } else {
      onAdd(petData);
    }
    setShowForm(false);
    setEditingPet(null);
  };

  const handleCancel = () => {
    setShowForm(false);
    setEditingPet(null);
  };

  const getGuardianName = (guardianId: string) => {
    const guardian = guardians.find((g) => g.id === guardianId);
    return guardian ? guardian.name : "Unknown Guardian";
  };

  return (
    <div>
      <div className="flex justify-between items-center mb-6">
        <h2 className="text-xl font-semibold">Pet Management</h2>
        <button
          onClick={() => setShowForm(true)}
          className="flex items-center gap-2 px-4 py-2 bg-blue-600 text-white rounded-md hover:bg-blue-700"
        >
          <PlusCircle className="h-5 w-5" />
          Add Pet
        </button>
      </div>

      <div className="grid gap-4">
        {pets.map((pet) => (
          <div
            key={pet.id}
            className="bg-white rounded-lg shadow p-4 flex items-center justify-between"
          >
            <div>
              <h3 className="font-semibold">{pet.name}</h3>
              <p className="text-sm text-gray-600">
                {pet.species} {pet.breed ? `- ${pet.breed}` : ""}
              </p>
              <p className="text-sm text-gray-600">Age: {pet.age}</p>
              <p className="text-sm text-gray-600">
                Guardian: {getGuardianName(pet.guardian?.id || "")}
              </p>
            </div>
            <div className="flex gap-2">
              <Link
                to={`/pets/${pet.id}`}
                className="p-2 text-gray-600 hover:text-blue-600 rounded-full hover:bg-blue-50"
              >
                <Eye className="h-5 w-5" />
              </Link>
              <button
                onClick={() => handleEdit(pet)}
                className="p-2 text-gray-600 hover:text-blue-600 rounded-full hover:bg-blue-50"
              >
                <Edit2 className="h-5 w-5" />
              </button>
              <button
                onClick={() => onDelete(pet.id)}
                className="p-2 text-gray-600 hover:text-red-600 rounded-full hover:bg-red-50"
              >
                <Trash2 className="h-5 w-5" />
              </button>
            </div>
          </div>
        ))}
      </div>

      {showForm && (
        <PetForm
          onSubmit={handleSubmit}
          onCancel={handleCancel}
          initialData={editingPet || undefined}
          guardians={guardians}
          onfindGuardian={onfindGuardian}
        />
      )}
    </div>
  );
};

export default PetList;
