import axios from "axios";
import React from "react";
import {Button, InputGroup, Table} from "react-bootstrap";
import Swal from "sweetalert2";


const GetAttendeeByDNI = () => {
    const [profile, setProfile] = React.useState({});

    const [dni, setDni] = React.useState("");

    const path = 'http://localhost:8080/';


    async function getData() {
        const headers = {
            "content-type": "application/json"
        };

        if (dni === '') {
            await Swal.fire({
                position: 'center',
                icon: 'warning',
                title: "The DNI cannot be empty",
                showConfirmButton: false,
                timer: 2000
            })
            return;
        }
        await axios.get(
            path + "api/v3.0.1/attendees/" + dni,
            {headers}
        ).then(async response => {
            await Swal.fire({
                position: 'center',
                icon: 'success',
                title: 'Attendee found successfully!',
                showConfirmButton: false,
                timer: 1000
            })
            setProfile(response.data);

        }).catch(async error => {
            if (error.response.status === 400) {
                await Swal.fire({
                    position: 'center',
                    icon: 'warning',
                    title: "Bad Request",
                    showConfirmButton: false,
                    timer: 2000
                })
                window.location.reload();
            } else if (error.response.status === 404) {
                await Swal.fire({
                    position: 'center',
                    icon: 'error',
                    title: "Attendee Not Found",
                    showConfirmButton: false,
                    timer: 2000
                })
                window.location.reload();
            }
        });
    }


    function handleChangeDni(e) {
        setDni(e.target.value);
    }


    return (
        <div>
            <div style={{width: "30rem", marginTop: "3rem", marginLeft: "35rem"}}>
                <InputGroup className="mb-3">
                    <input
                        type="text"
                        className="form-control dark"
                        id="dni"
                        placeholder="DNI from Attendee"
                        name="dni"
                        value={dni}
                        onChange={handleChangeDni}
                    />
                    <Button type="submit" onClick={() => {
                        getData().then(r => r)
                    }}
                    > Search
                    </Button>
                </InputGroup>
                <Table
                    bordered
                    hover
                    variant="dark"
                    style={tableStyle}
                >
                    <thead style={tableStyle}>
                    <tr>
                        <th>Dni</th>
                        <th>Name</th>
                        <th>Surname</th>
                        <th>Phone</th>
                        <th>BirthDate</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr key={profile.dni}>
                        <td>{profile.dni}</td>
                        <td>{profile.name}</td>
                        <td>{profile.surname}</td>
                        <td>{profile.phone}</td>
                        <td>{profile.birthDate}</td>
                    </tr>
                    </tbody>
                </Table>
            </div>
        </div>
    );
}

export default GetAttendeeByDNI;

const tableStyle = {
    textAlign: 'center',
    width: '100%',
    marginTop: "3rem",
    marginBottom: '5%'
}