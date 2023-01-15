import {BrowserRouter, Route, Routes} from "react-router-dom";
import Home from "./components/Home";
import RegisterAttendee from "./components/RegisterAttendee";
import Navigator from "./components/Navigator";
import './App.css';
import GetAttendeeByDNI from "./components/GetAttendeeByDni";

function App() {
    return (
        <div className="App">
            <Navigator/>
            <BrowserRouter>
                <Routes>
                    <Route path="/" element={<Home/>}/>
                    <Route path="/register" element={<RegisterAttendee/>}/>
                    <Route path="/attendee" element={<GetAttendeeByDNI/>}/>
                </Routes>
            </BrowserRouter>
        </div>
    );
}

export default App;
