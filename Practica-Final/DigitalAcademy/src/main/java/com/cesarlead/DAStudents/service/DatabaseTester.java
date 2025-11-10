package com.cesarlead.DAStudents.service;

import com.cesarlead.DAStudents.model.Student;
import com.cesarlead.DAStudents.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DatabaseTester implements CommandLineRunner {
	private final StudentRepository studentRepository;

	public DatabaseTester(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("--- Probando la comunicacion con la base de datos ---");

		long count = studentRepository.count();
		System.out.println("Numero actual de estudiantes: " + count);

		List<Student> students = studentRepository.findAll();
		System.out.println("Resultado de findAll: " + students.size());
	}
}
