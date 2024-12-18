import React, { useState, useEffect } from "react";
import { Pet, User } from "../../types";
import { X, Search } from "lucide-react";

interface PetFormProps {
  onSubmit: (pet: Omit<Pet, "id">) => void;
  onCancel: () => void;
  initialData?: Pet;
  guardians: User[];
  onfindGuardian: (name: string) => Promise<User[]>;
}

export const PetForm: React.FC<PetFormProps> = ({
  onSubmit,
  onCancel,
  initialData,
  onfindGuardian,
}) => {
  const [formData, setFormData] = useState({
    name: initialData?.name || "",
    species: initialData?.species || "",
    breed: initialData?.breed || "",
    age: initialData?.age || 0,
    guardianId: initialData?.guardianId || initialData?.user?.id || "",
    user: initialData?.user || "",
  });

  const [searchTerm, setSearchTerm] = useState("");
  const [filteredGuardians, setFilteredGuardians] = useState<User[]>([]);
  console.log(initialData);
  useEffect(() => {
    if (searchTerm.length < 3) {
      if (initialData?.user) {
        initialData.guardianId = initialData.user.id;
        setFilteredGuardians([initialData.user]); // Muestra el guardián actual si
      }
      // setFilteredGuardians([]); // Vacía los resultados si el término es demasiado corto
      return;
    }

    const fetchGuardians = async () => {
      try {
        const response = await onfindGuardian(searchTerm.toLowerCase());
        console.log(response); // Muestra los resultados en la consola
        setFilteredGuardians(response); // Actualiza el estado con los guardianes encontrados
      } catch (error) {
        console.error("Error fetching guardians:", error);
        setFilteredGuardians([]); // Vacía los resultados en caso de error
      }
    };

    fetchGuardians();
  }, [searchTerm]);

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    onSubmit(formData);
  };

  const selectedGuardian = filteredGuardians.find(
    (g) => g.id == formData.guardianId
  );

  return (
    <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center p-4 z-50">
      <div className="bg-white rounded-lg shadow-xl p-6 w-full max-w-md">
        <div className="flex justify-between items-center mb-4">
          <h2 className="text-xl font-semibold">
            {initialData ? "Edit Pet" : "Add New Pet"}
          </h2>
          <button
            onClick={onCancel}
            className="text-gray-500 hover:text-gray-700"
          >
            <X className="h-5 w-5" />
          </button>
        </div>

        <form onSubmit={handleSubmit} className="space-y-4">
          <div>
            <label className="block text-sm font-medium text-gray-700">
              Name
            </label>
            <input
              type="text"
              value={formData.name}
              onChange={(e) =>
                setFormData({ ...formData, name: e.target.value })
              }
              className="mt-1 block w-full rounded-md border border-gray-300 px-3 py-2 shadow-sm focus:border-blue-500 focus:outline-none focus:ring-1 focus:ring-blue-500"
              required
            />
          </div>

          <div>
            <label className="block text-sm font-medium text-gray-700">
              Species
            </label>
            <input
              type="text"
              value={formData.species}
              onChange={(e) =>
                setFormData({ ...formData, species: e.target.value })
              }
              className="mt-1 block w-full rounded-md border border-gray-300 px-3 py-2 shadow-sm focus:border-blue-500 focus:outline-none focus:ring-1 focus:ring-blue-500"
              required
            />
          </div>

          <div>
            <label className="block text-sm font-medium text-gray-700">
              Breed (Optional)
            </label>
            <input
              type="text"
              value={formData.breed}
              onChange={(e) =>
                setFormData({ ...formData, breed: e.target.value })
              }
              className="mt-1 block w-full rounded-md border border-gray-300 px-3 py-2 shadow-sm focus:border-blue-500 focus:outline-none focus:ring-1 focus:ring-blue-500"
            />
          </div>

          <div>
            <label className="block text-sm font-medium text-gray-700">
              Age
            </label>
            <input
              type="number"
              value={formData.age}
              onChange={(e) =>
                setFormData({ ...formData, age: parseInt(e.target.value) })
              }
              className="mt-1 block w-full rounded-md border border-gray-300 px-3 py-2 shadow-sm focus:border-blue-500 focus:outline-none focus:ring-1 focus:ring-blue-500"
              required
              min="0"
            />
          </div>

          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">
              Guardian
            </label>
            <div className="relative">
              <div className="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                <Search className="h-4 w-4 text-gray-400" />
              </div>
              <input
                type="text"
                placeholder="Type at least 3 characters to search user..."
                value={searchTerm}
                onChange={(e) => setSearchTerm(e.target.value)}
                className="block w-full pl-10 pr-3 py-2 border border-gray-300 rounded-md leading-5 bg-white placeholder-gray-500 focus:outline-none focus:border-blue-500 focus:ring-1 focus:ring-blue-500 sm:text-sm"
              />
            </div>

            {searchTerm.length > 0 && searchTerm.length < 3 && (
              <p className="mt-2 text-sm text-gray-500">
                Please type at least 3 characters to search
              </p>
            )}

            {searchTerm.length >= 3 && (
              <div className="mt-2 max-h-40 overflow-y-auto border border-gray-200 rounded-md">
                {Array.isArray(filteredGuardians) &&
                filteredGuardians.length > 0 ? (
                  filteredGuardians.map((user) => (
                    <label
                      key={user.id}
                      className={`flex items-center p-2 hover:bg-gray-50 cursor-pointer ${
                        formData.guardianId === user.id ? "bg-blue-50" : ""
                      }`}
                    >
                      <input
                        type="radio"
                        name="user"
                        value={user.id}
                        checked={formData.guardianId === user.id}
                        onChange={(e) =>
                          setFormData({
                            ...formData,
                            guardianId: e.target.value,
                          })
                        }
                        className="h-4 w-4 text-blue-600 focus:ring-blue-500"
                      />
                      <div className="ml-3">
                        <p className="text-sm font-medium text-gray-700">
                          {user.name}
                        </p>
                        <p className="text-xs text-gray-500">
                          {user.email}
                        </p>
                      </div>
                    </label>
                  ))
                ) : (
                  <div className="p-2 text-sm text-gray-500 text-center">
                    No guardians found
                  </div>
                )}
              </div>
            )}

            {selectedGuardian && (
              <p className="mt-2 text-sm text-gray-600">
                Selected: {selectedGuardian.name}
              </p>
            )}
          </div>

          <div className="flex justify-end space-x-3 mt-6">
            <button
              type="button"
              onClick={onCancel}
              className="px-4 py-2 border border-gray-300 rounded-md text-gray-700 hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500"
            >
              Cancel
            </button>
            <button
              type="submit"
              className="px-4 py-2 bg-blue-600 text-white rounded-md hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500"
            >
              {initialData ? "Update" : "Add"} Pet
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default PetForm;
