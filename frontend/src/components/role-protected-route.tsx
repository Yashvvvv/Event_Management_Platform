import { ReactNode } from "react";
import { useAuth } from "react-oidc-context";
import { Navigate, useLocation } from "react-router";
import { useRoles } from "@/hooks/use-roles";

interface RoleProtectedRouteProperties {
  children: ReactNode;
  allowedRoles: ("ORGANIZER" | "ATTENDEE" | "STAFF")[];
}

const RoleProtectedRoute: React.FC<RoleProtectedRouteProperties> = ({ 
  children, 
  allowedRoles 
}) => {
  const { isLoading: isAuthLoading, isAuthenticated } = useAuth();
  const { isLoading: isRolesLoading, isOrganizer, isAttendee, isStaff } = useRoles();
  const location = useLocation();

  if (isAuthLoading || isRolesLoading) {
    return <p>Loading...</p>;
  }

  if (!isAuthenticated) {
    localStorage.setItem(
      "redirectPath",
      globalThis.location.pathname + globalThis.location.search,
    );
    return <Navigate to="/login" state={{ from: location }} replace />;
  }

  // Check if user has at least one of the allowed roles
  const hasRequiredRole = allowedRoles.some(role => {
    switch (role) {
      case "ORGANIZER":
        return isOrganizer;
      case "ATTENDEE":
        return isAttendee;
      case "STAFF":
        return isStaff;
      default:
        return false;
    }
  });

  if (!hasRequiredRole) {
    // Redirect to dashboard which will redirect based on their actual role
    return <Navigate to="/dashboard" replace />;
  }

  return children;
};

export default RoleProtectedRoute;
