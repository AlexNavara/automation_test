package com.navara.rest.links;

import com.navara.rest.models.Student;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Path("/students")
public class StudentsResource {

    private static final Map<Integer, Student> students = new ConcurrentHashMap<>();
    private static final AtomicInteger counter = new AtomicInteger();

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response findAllStudents() {
        String response = students.values().stream()
                .map(Student::toString)
                .collect(Collectors.joining("\n"));
        return Response.ok(response).build();
    }

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    public Response createStudent(final String name) {
        Student student = new Student();
        student.setId(counter.getAndIncrement());
        student.setName(name);
        students.put(student.getId(), student);
        return Response.ok().status(201).build();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{id}")
    public Response getStudent(@PathParam("id") Integer id) {
        if (!students.containsKey(id))
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Student with id " + id + " not found")
                    .build();
        return Response.ok(students.get(id).toString()).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteStudent(@PathParam("id") Integer id) {
        if (!students.containsKey(id))
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Student with id " + id + " not found")
                    .build();
        students.remove(id);
        return Response.ok().build();
    }

}
