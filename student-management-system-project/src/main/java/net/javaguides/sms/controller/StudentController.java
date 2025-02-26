package net.javaguides.sms.controller;

import net.javaguides.sms.dto.StudentDto;
import net.javaguides.sms.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

import java.util.List;

@Controller
public class StudentController {

    private StudentService studentService;
// we are injecting this interface as dependency in a student controller class using this constructor based dependency injection
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    // handler method to handle list students request
    @GetMapping("/students")
    public String listStudents(Model model){
        List<StudentDto> students = studentService.getAllStudents();
        model.addAttribute("students",students);
        return "students";
    }
    
    // handler method to handle new Student request(in html file we provide this link @{/student/new} which handles when new student icon is press 
    @GetMapping("/students/new")
    public String newStudent(Model model) {
    		// student model object to store student from data
    	StudentDto studentDto=new StudentDto();
    	model.addAttribute("student",studentDto);
    		return "create_student";
    }
    // handler method to handle save student from submit (hit) request
    @PostMapping("/students")
    public String saveStudent(@Valid @ModelAttribute("student")StudentDto student,
    		                                                 BindingResult  result,
    		                                                   Model  model                ){
    	  if(result.hasErrors()) {
    		  model.addAttribute("student",student);
    		  return "create_student"; //return same view that is to create when any error came
    	  }
    	// this will save that form data into database and then it will redirect that user to the list page of getallstudent above by /students
    	studentService.createStudent(student);
    	return "redirect:/students";
    	
    }
    // handler method to handle edit student request
    @GetMapping("/students/{studentId}/edit")
    public String editStudent(@PathVariable("studentId")Long studentId,
    		                                    Model model) {
    	StudentDto student= studentService.getStudentById(studentId);
    	model.addAttribute("student",student);
    	return "edit_student";
    }
    // handler method to handle edit student form submit request
    @PostMapping("/students/{studentId}")
    public String updateStudent(@PathVariable("studentId")Long studentId,
    		                   @Valid @ModelAttribute("student") StudentDto studentDto,
    		                   BindingResult result,
    		                   Model model){
    	if(result.hasErrors()) {
    		model.addAttribute("student",studentDto);
    		return "edit_student";
    	}
    	studentDto.setId(studentId);
    	studentService.updateStudent(studentDto);
    	return "redirect:/students";// once user edit the page we need to redirect that user to the list student's page ...so doing this
    	
    }
    // handler method to handle delete student request
    @GetMapping("/students/{studentId}/delete")
    public String DeleteStudent(@PathVariable("studentId")Long studentId) {
    	studentService.deleteStudent(studentId);
    	return "redirect:/students";// once user delete any student record from the page then after it  we need to redirect that user to the list student's page ...so doing this
    }
    // handler method to handle view student request 
    @GetMapping("/students/{studentId}/view")
    public String viewStudent(@PathVariable("studentId")Long studentId,
    		                  Model model) {
    	StudentDto studentDto = studentService.getStudentById(studentId);
    	model.addAttribute("student",studentDto);
    	return "view_student";
    			
    }
}
