import React from "react";
import { User } from "../../types";

interface UserListProps {
  users: User[];
  onAdd: (user: Omit<User, "id">) => void;
  onUpdate: (id: string, user: Partial<User>) => void;
  onDelete: (id: string) => void;
}

const UserList: React.FC<UserListProps> = ({ users, onAdd, onUpdate, onDelete }) => {
  return (
      <div>
        <h2>User Management</h2>
        <button
            onClick={() =>
                onAdd({
                  firstName: "Nuevo",
                  lastName: "Usuario",
                  email: "example@mail.com",
                  phoneNumber: "123456789",
                })
            }
        >
          Add User
        </button>

        <ul>
          {users.map((user) => (
              <li key={user.id}>
                {user.firstName} {user.lastName} ({user.email})
                <button onClick={() => onDelete(user.id)}>Delete</button>
                <button onClick={() => onUpdate(user.id, { email: "updated@mail.com" })}>
                  Update
                </button>
              </li>
          ))}
        </ul>
      </div>
  );
};

export default UserList;
