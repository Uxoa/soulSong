import React from "react";

const Dashboard: React.FC = () => {
  return (
      <div className="p-6 max-w-6xl mx-auto">
        <h1 className="text-3xl font-bold mb-6">Dashboard</h1>

        {/* Contenido Específico del Dashboard */}
        <div className="bg-blue-100 p-4 mb-6 rounded">
          <h2 className="text-xl font-bold">Welcome to the Dashboard!</h2>
          <p className="text-gray-700">
            Here you can see a summary of our free benefits.
          </p>
        </div>
      </div>
  );
};

export default Dashboard;

