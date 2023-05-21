import axios from "axios";

const BASE_API_URL = "http://localhost:8080/students";

class StudentService {
    saveStudent(student) {
        return axios.post(`${BASE_API_URL}/save`, student);
    }

    getStudentById(id) {
        return axios.get(`${BASE_API_URL}/${id}`);
    }

    getAllStudents() {
        return axios.get(`${BASE_API_URL}/all`);
    }

    updateStudent(id, updatedStudent) {
        return axios.put(`${BASE_API_URL}/${id}`, updatedStudent);
    }

    deleteStudent(id) {
        return axios.delete(`${BASE_API_URL}/${id}`);
    }
}

export default new StudentService();
