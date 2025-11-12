package com.cesarlead.DACourses.service;

import com.cesarlead.DACourses.model.Course;
import com.cesarlead.DACourses.repository.CourseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DatabaseTester implements CommandLineRunner {
	private final CourseRepository courseRepository;

	public DatabaseTester(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("--- Probando la comunicacion con la base de datos ---");

		long count = courseRepository.count();
		System.out.println("Numero actual de estudiantes: " + count);

		List<Course> students = courseRepository.findAll();
		System.out.println("Resultado de findAll: " + students.size());
	}
}
