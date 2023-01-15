import React, {useEffect} from "react";
import {Image, Table} from "react-bootstrap";
import IconDelete from "../images/icon-delete.png";
import axios from "axios";
import Swal from "sweetalert2";

const Home = () => {
    const path = 'http://localhost:8080/';
    const [attendeesList, setAttendeesList] = React.useState([]);

    async function getAttendees() {
        const headers = {
            "content-type": "application/json"
        };

        const response = await axios.get(
            path + "api/v3.0.1/attendees",
            {
                headers,
            }
        );
        const data = response.data;
        setAttendeesList(data);
    }

    useEffect(() => {
        getAttendees().then(r => r)
    }, []);

    return (
        <div>
            {attendeesList.length > 0 && (
                <div>
                    <div style={cardStyle}>
                        <label>List of Assistants</label>
                    </div>

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
                            <th>Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        {attendeesList.map((attendee) => (
                            <tr key={attendee.dni}>
                                <td>{attendee.dni}</td>
                                <td>{attendee.name}</td>
                                <td>{attendee.surname}</td>
                                <td>{attendee.phone}</td>
                                <td>{attendee.birthDate}</td>
                                <td>
                                    <Image style={{width: "2rem"}}
                                           src={IconDelete}
                                           onClick={() => {
                                               const headers = {
                                                   "content-type": "application/json"
                                               };
                                               axios.delete(
                                                   path + "api/v3.0.1/attendees/" + attendee.dni,
                                                   {headers},
                                               ).then(async res => {
                                                   if (res) {
                                                       await Swal.fire({
                                                           position: 'center',
                                                           icon: 'success',
                                                           title: 'Attendee deleted successfully!',
                                                           showConfirmButton: false,
                                                           timer: 2000
                                                       })
                                                       window.location.reload()
                                                   }
                                               });
                                           }}
                                    />
                                </td>
                            </tr>
                        ))}
                        </tbody>
                    </Table>
                </div>
            )}
        </div>
    );
};

export default Home;

const cardStyle = {
    textAlign: "center",
    fontSize: "5rem",
    marginLeft: "5rem",
    marginTop: "3rem",
    color: 'black',
}

const tableStyle = {
    textAlign: 'center',
    marginLeft: '10%',
    width: '80%',
    marginBottom: '5%'
}