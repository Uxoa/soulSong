import React, { useEffect, useState } from "react";
import { useParams, Link } from "react-router-dom";
import { format, isPast } from "date-fns";
import {
  PawPrint,
  User,
  Calendar,
  Clock,
  ArrowLeft,
  PlusCircle,
} from "lucide-react";
import { usePets } from "../../hooks/usePets";
import { useGuardians } from "../../hooks/useUser.ts";
import { useAppointments } from "../../hooks/useAppointments";
import AppointmentForm from "../appointments/AppointmentForm";
import { Appointment, User, Pet } from "../../types";
import {
  createAppointment,
  getNextAppointmentByPetId,
  getPastAppointmentsByPetId,
} from "../../api-services/appointment.services";

const PetDetail: React.FC = () => {
  const { id } = useParams<{ id: string }>();
  const { getPetById } = usePets();

  const { getAppointmentsByPetId, addAppointment } = useAppointments();
  const [showAppointmentForm, setShowAppointmentForm] = useState(false);

  const [pet, setPet] = useState<Pet | null>(null);
  const [guardian, setGuardian] = useState<User | null>(null);
  const [futureAppointments, setFutureAppointments] = useState<Appointment[]>(
    []
  );
  const [pastAppointments, setPastAppointments] = useState<Appointment[]>([]);
  //   let guardian = pet ? getGuardianById(pet.guardianId) : null;

  const fetchPet = async () => {
    const pet = await getPetById(id || "");
    setPet(pet || null);
    setGuardian(pet.guardian);
    const next = await getNextAppointmentByPetId(id || "");
    const past = await getPastAppointmentsByPetId(id || "");

    setFutureAppointments(next);
    setPastAppointments(past);
  };

  useEffect(() => {
    fetchPet();
  }, [id]);

  //   const petAppointments = getAppointmentsByPetId(id || "");

  //   const pastAppointments = petAppointments.filter((apt) => isPast(apt.date));
  //   const futureAppointments = petAppointments.filter((apt) => !isPast(apt.date));

  if (!pet) {
    return (
      <div className="flex-1 p-8">
        <div className="text-center text-gray-600">Pet not found</div>
      </div>
    );
  }

  const handleAddAppointment = (
    appointmentData: Omit<(typeof petAppointments)[0], "id">
  ) => {
    createAppointment(appointmentData);
    // addAppointment(appointmentData);
    fetchPet();
    setShowAppointmentForm(false);
  };

  const AppointmentCard = ({
    appointment,
    isPast,
  }: {
    appointment: (typeof petAppointments)[0];
    isPast: boolean;
  }) => (
    <div className="bg-white rounded-lg shadow p-4 hover:shadow-md transition-shadow">
      <div className="flex justify-between items-start mb-3">
        <div className="flex items-center gap-2">
          <Calendar className="h-5 w-5 text-blue-600" />
          <span className="font-medium">
            {format(appointment.date, "MMMM d, yyyy")}
          </span>
        </div>
        {/* <span
          className={`px-3 py-1 rounded-full text-sm ${
            appointment.status === "completed"
              ? "bg-green-100 text-green-800"
              : appointment.status === "cancelled"
              ? "bg-red-100 text-red-800"
              : "bg-blue-100 text-blue-800"
          }`}
        >
          {appointment.status.charAt(0).toUpperCase() +
            appointment.status.slice(1)}
        </span> */}
      </div>
      <div className="flex items-center gap-2 text-gray-600 mb-2">
        <Clock className="h-4 w-4" />
        <span>{appointment.time}</span>
      </div>
      <p className="text-gray-700">{appointment.reason}</p>
      {appointment.notes && (
        <p className="mt-2 text-sm text-gray-600">{appointment.notes}</p>
      )}
    </div>
  );

  return (
    <div className="flex-1 p-8">
      <div className="max-w-4xl mx-auto">
        <Link
          to="/pets"
          className="inline-flex items-center gap-2 text-blue-600 hover:text-blue-700 mb-6"
        >
          <ArrowLeft className="h-4 w-4" />
          Back to Pets
        </Link>

        {/* Pet Information Card */}
        <div className="bg-white rounded-lg shadow-md p-6 mb-8">
          <div className="flex items-center gap-3 mb-6">
            <div className="bg-blue-100 p-3 rounded-full">
              <PawPrint className="h-6 w-6 text-blue-600" />
            </div>
            <div>
              <h1 className="text-2xl font-bold text-gray-800">{pet.name}</h1>
              <p className="text-gray-600">
                {pet.species} {pet.breed ? `- ${pet.breed}` : ""}
              </p>
            </div>
          </div>

          <div className="flex items-center gap-3 text-gray-600 mb-4">
            <User className="h-5 w-5" />
            <Link
              to={`/guardians/${guardian.id}`}
              className="text-blue-600 hover:text-blue-700"
            >
              {guardian.name}
            </Link>
          </div>

          <p className="text-gray-600">Age: {pet.age} years</p>
        </div>

        {/* Appointments Section */}
        <div className="space-y-8">
          <div className="flex justify-between items-center">
            <h2 className="text-xl font-semibold">Appointments</h2>
            <button
              onClick={() => setShowAppointmentForm(true)}
              className="flex items-center gap-2 px-4 py-2 bg-blue-600 text-white rounded-md hover:bg-blue-700"
            >
              <PlusCircle className="h-5 w-5" />
              Schedule Appointment
            </button>
          </div>

          {/* Future Appointments */}
          <div>
            <h3 className="text-lg font-semibold mb-4">
              Upcoming Appointments
            </h3>
            {futureAppointments.length === 0 ? (
              <p className="text-gray-600 bg-white rounded-lg shadow p-4">
                No upcoming appointments
              </p>
            ) : (
              <div className="grid gap-4">
                {futureAppointments.map((appointment) => (
                  <AppointmentCard
                    key={appointment.id}
                    appointment={appointment}
                    isPast={false}
                  />
                ))}
              </div>
            )}
          </div>

          {/* Past Appointments */}
          <div>
            <h3 className="text-lg font-semibold mb-4">Past Appointments</h3>
            {pastAppointments.length === 0 ? (
              <p className="text-gray-600 bg-white rounded-lg shadow p-4">
                No past appointments
              </p>
            ) : (
              <div className="grid gap-4">
                {pastAppointments.map((appointment) => (
                  <AppointmentCard
                    key={appointment.id}
                    appointment={appointment}
                    isPast={true}
                  />
                ))}
              </div>
            )}
          </div>
        </div>
      </div>

      {showAppointmentForm && (
        <AppointmentForm
          onSubmit={handleAddAppointment}
          onCancel={() => setShowAppointmentForm(false)}
          petId={id || ""}
        />
      )}
    </div>
  );
};

export default PetDetail;
