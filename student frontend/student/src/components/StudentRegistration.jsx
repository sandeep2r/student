import React, { useState } from "react";
import StudentSerivce from "../service/StudentSerivce";

const subjects = [
    {
        name: "Math",
        code: "MATH101",
        theoryOrPractical: "Theory"
    },
    {
        name: "Science",
        code: "SCI101",
        theoryOrPractical: "Practical"
    },
    {
        name: "English",
        code: "ENG101",
        theoryOrPractical: "Theory"
    }
];

const StudentRegistration = () => {
    const [student, setStudent] = useState({
        name: "",
        dateOfBirth: "",
        address: "",
        mobile: "",
        subjects: []
    });

    const [selectedSubject, setSelectedSubject] = useState("");
    const [subjectCode, setSubjectCode] = useState("");
    const [selectedType, setSelectedType] = useState("");

    const handleSubjectChange = (e) => {
        const subject = subjects.find((subj) => subj.name === e.target.value);
        setSelectedSubject(e.target.value);
        setSubjectCode(subject ? subject.code : "");
        setSelectedType(subject ? subject.theoryOrPractical : "");
    };

    const handleSubmit = (e) => {
        e.preventDefault();

        const updatedStudent = {
            ...student,
            subjects: [
                ...student.subjects,
                {
                    name: selectedSubject,
                    code: subjectCode,
                    theoryOrPractical: selectedType
                }
            ]
        };

        StudentSerivce.saveStudent(updatedStudent)
            .then((response) => {
                alert("Student registration successful");
                console.log("Student registration successful:", response.data);
            })
            .catch((error) => {
                console.error("Error registering student:", error);
            });
    };

    return (
        <div className="container mt-5 col-md-3">
            <h2>Student Registration</h2>
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label htmlFor="name">Name:</label>
                    <input
                        type="text"
                        id="name"
                        className="form-control"
                        name="name"
                        value={student.name}
                        onChange={(e) =>
                            setStudent((prevStudent) => ({
                                ...prevStudent,
                                name: e.target.value
                            }))
                        }
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="dateOfBirth">Date of Birth:</label>
                    <input
                        type="date"
                        id="dateOfBirth"
                        className="form-control"
                        name="dateOfBirth"
                        value={student.dateOfBirth}
                        onChange={(e) =>
                            setStudent((prevStudent) => ({
                                ...prevStudent,
                                dateOfBirth: e.target.value
                            }))
                        }
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="address">Address:</label>
                    <input
                        type="text"
                        id="address"
                        className="form-control"
                        name="address"
                        value={student.address}
                        onChange={(e) =>
                            setStudent((prevStudent) => ({
                                ...prevStudent,
                                address: e.target.value
                            }))
                        }
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="mobile">Mobile:</label>
                    <input
                        type="text"
                        id="mobile"
                        className="form-control"
                        name="mobile"
                        value={student.mobile}
                        onChange={(e) =>
                            setStudent((prevStudent) => ({
                                ...prevStudent,
                                mobile: e.target.value
                            }))
                        }
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="subject">Subject Name:</label>
                    <select
                        id="subject"
                        className="form-control"
                        value={selectedSubject}
                        onChange={handleSubjectChange}
                    >
                        <option value="">Select Subject</option>
                        {subjects.map((subject, index) => (
                            <option key={index} value={subject.name}>
                                {subject.name}
                            </option>
                        ))}
                    </select>
                </div>
                <div className="form-group">
                    <label htmlFor="code">Code:</label>
                    <input
                        type="text"
                        id="code"
                        className="form-control"
                        value={subjectCode}
                        readOnly
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="type">Type:</label>
                    <select
                        id="type"
                        className="form-control"
                        value={selectedType}
                        onChange={(e) => setSelectedType(e.target.value)}
                    >
                        <option value="">Select Type</option>
                        <option value="Theory">Theory</option>
                        <option value="Practical">Practical</option>
                    </select>
                </div>
                <button type="submit" className="btn btn-primary mt-3">
                    Register
                </button>
            </form>
        </div>
    );
};

export default StudentRegistration;
