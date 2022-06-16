import React from "react";
import axios from "axios";
import {useNavigate} from "react-router-dom";
import Swal from "sweetalert2";
import {Container} from "react-bootstrap";

const RegisterAttendee = () => {
    const [values, setValues] = React.useState({
        dni: "",
        name: "",
        surname: "",
        phone: "",
        birthDate: "",
        dniScanUrl: "",
    });

    const path = 'http://localhost:8080/';

    const navigate = useNavigate();

    async function handleSubmit(e) {
        e.preventDefault();

        if (
            !(values.dni === "") &&
            !(values.name === "") &&
            !(values.surname === "") &&
            !(values.phone === "") &&
            !(values.birthDate === "") &&
            !(values.dniScanUrl === "")
        ) {
            await axios.post(
                path + "api/v3.0.1/attendees",
                values
            ).then(async response => {
                if (response.status === 200) {
                    await Swal.fire({
                        position: 'center',
                        icon: 'success',
                        title: 'Attendee registered successfully!',
                        showConfirmButton: false,
                        timer: 2000
                    })
                    navigate("/");
                }
            }).catch(async err =>
                await Swal.fire({
                    position: 'center',
                    icon: 'error',
                    title: err.code,
                    showConfirmButton: false,
                    timer: 2000
                })
            );
        }
    }

    function handleChange(e) {
        const {target} = e;
        const {name, value} = target;

        const newValues = {
            ...values,
            [name]: value,
        };

        setValues(newValues);
    }


    return (
        <Container fluid="md">
            <div className="register" style={cardStyle}>
                <section className="form">
                    <h1>Register Attendee</h1>
                    <form onSubmit={handleSubmit}>
                        <label className="form-label">DNI</label>
                        <input
                            type="text"
                            style={inputs}
                            className="form-control"
                            id="dni"
                            name="dni"
                            value={values.dni}
                            onChange={handleChange}
                        />

                        <label className="form-label">Name</label>
                        <input
                            type="text"
                            style={inputs}
                            className="form-control"
                            id="name"
                            name="name"
                            value={values.name}
                            onChange={handleChange}
                        />

                        <label className="form-label">Surname</label>
                        <input
                            type="text"
                            style={inputs}
                            className="form-control"
                            id="surname"
                            name="surname"
                            value={values.surname}
                            onChange={handleChange}
                        />

                        <label className="form-label">Phone</label>
                        <input
                            type="text"
                            style={inputs}
                            className="form-control"
                            id="phone"
                            name="phone"
                            value={values.phone}
                            onChange={handleChange}
                        />

                        <label className="form-label">Bird Date</label>
                        <input
                            type="text"
                            style={inputs}
                            className="form-control"
                            id="birthDate"
                            name="birthDate"
                            value={values.birthDate}
                            onChange={handleChange}
                        />

                        <label className="form-label">Dni Scan Url</label>
                        <input
                            type="text"
                            style={inputs}
                            className="form-control"
                            id="dniScanUrl"
                            name="dniScanUrl"
                            value={values.dniScanUrl}
                            onChange={handleChange}
                        />
                        <div style={{padding: "1rem"}}>
                            <button className="btn btn-primary" type="submit">
                                Sign up
                            </button>
                        </div>

                    </form>
                </section>
            </div>
        </Container>

    );
};

export default RegisterAttendee;


const cardStyle = {
    width: '32rem',
    padding: "3rem",
    textAlign: "center",
    marginTop: '2rem',
    marginLeft: '2rem',
    background: '#000',
    color: 'white',
}
const inputs = {
    width: "20rem",
    marginLeft: "3rem"
}