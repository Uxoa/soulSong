import { useEffect, useState } from "react";
import { User } from "../types";
import * as userService from "../api-services/users.services";

export const useUsers = () => {
  const [users, setUsers] = useState<User[]>([]); // Inicializado como array vacío
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const data = await userService.fetchUsers();
        setUsers(Array.isArray(data) ? data : []); // Verifica que sea un array
      } catch (err) {
        console.error("Error fetching users:", err);
        setError("Failed to fetch users");
      }
    };

    fetchData();
  }, []);

  const addUser = async (user: Omit<User, "id">) => {
    const newUser = await userService.addUser(user);
    setUsers([...users, newUser]);
  };

  const updateUser = async (id: string, updatedUser: Partial<User>) => {
    const user = await userService.updateUser(id, updatedUser);
    setUsers(users.map((u) => (u.id === id ? user : u)));
  };

  const deleteUser = async (id: string) => {
    await userService.deleteUser(id);
    setUsers(users.filter((u) => u.id !== id));
  };

  return { users, addUser, updateUser, deleteUser, error };
};
