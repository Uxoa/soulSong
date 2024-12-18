import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { Mail, Phone } from "lucide-react";

import { getUserById } from "../../api-services/users.services";
import { User } from "../../types";

const UserDetail: React.FC = () => {
  const { id } = useParams<{ id: string }>();

  const [user, setUser] = useState<User | null>(null);

  // Obtener los detalles del usuario
  const fetchData = async () => {
    try {
      const data = await getUserById(id || "");
      setUser(data);
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
          {/* User Information Card */}
          <div className="bg-white rounded-lg shadow-md p-6 mb-8">
            <h1 className="text-2xl font-bold text-gray-800 mb-6">
              {user.firstName} {user.lastName}
            </h1>

            <div className="space-y-4">
              <div className="flex items-center gap-3 text-gray-600">
                <Mail className="h-5 w-5" />
                <span>{user.email}</span>
              </div>

              <div className="flex items-center gap-3 text-gray-600">
                <Phone className="h-5 w-5" />
                <span>{user.phoneNumber}</span>
              </div>
            </div>
          </div>
        </div>
      </div>
  );
};

export default UserDetail;
