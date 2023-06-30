//package br.com.imd.pdse.monitorando;
//
//import br.com.imd.pdse.monitorando.domain.Classroom;
//import br.com.imd.pdse.monitorando.domain.User;
//
//import java.util.Collections;
//import java.util.List;
//import java.util.UUID;
//
//public class Util {
//
////    public static List<Classroom> getByUserType(User user){
////        List<Classroom> classrooms;
////
////        if (user.getMonitor() != null)
////            classrooms = user.getMonitor().getClassroom();
////        else if (user.getTeacher() != null)
////            classrooms = user.getTeacher().getClassrooms();
////        else if (user.getStudent() != null)
////            classrooms = user.getStudent().getClassrooms();
////        else
////            classrooms = Collections.emptyList();
////
////        return classrooms;
////    }
//
//    public static UUID getUUIDByUserType(User user){
//        UUID uuid;
//
//        if (user.getMonitor() != null)
//            uuid = user.getMonitor().getUuid();
//        else if (user.getTeacher() != null)
//            uuid = user.getTeacher().getUuid();
//        else if (user.getStudent() != null)
//            uuid = user.getStudent().getUuid();
//        else
//            uuid = null;
//
//        return uuid;
//    }
//}
