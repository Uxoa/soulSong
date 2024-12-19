import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { Mail, Music } from "lucide-react";

import { getUserById } from "../../api-services/users.services";
import { User } from "../../types";

const UserDetail: React.FC = () => {
  const { id } = useParams<{ id: string }>();
  const [user, setUser] = useState<User | null>(null);

  const fetchData = async () => {
    try {
      if (!id) throw new Error("Invalid user ID");
      const data = await getUserById(id);
      setUser(data); // Aseguramos que `data` cumple con el tipo `User`.
    } catch (error) {
      console.error("Error fetching user details:", error);
    }
  };

  useEffect(() => {
    fetchData();
  }, [id]);

  if (!user) {
    return (
        <div className="flex-1 p-8">
          <div className="text-center text-gray-600">User not found</div>
        </div>
    );
  }

  return (
      <div className="flex-1 p-8">
        <div className="max-w-4xl mx-auto">
          <div className="bg-white rounded-lg shadow-md p-6 mb-8">
            <h1 className="text-2xl font-bold text-gray-800 mb-6">{user.name}</h1>

            <div className="space-y-4">
              <div className="flex items-center gap-3 text-gray-600">
                <Mail className="h-5 w-5" />
                <span>{user.email}</span>
              </div>

              <div className="flex items-center gap-3 text-gray-600">
                <Music className="h-5 w-5" />
                <span>
                {user.favoriteSongs.length > 0
                    ? user.favoriteSongs.join(", ")
                    : "No favorite songs"}
              </span>
              </div>
            </div>
          </div>
        </div>
      </div>
  );
};

export default UserDetail;
