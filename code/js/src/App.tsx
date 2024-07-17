import './App.css';
import {useLoggedIn} from "./Utils/Session";
import {Navigate, Route, Routes} from "react-router-dom";
import routes from "./Utils/routesConfig";
import {Uris} from "./Utils/navigation/Uris";
import NavBar from "./Layouts/NavBar";
import LOGIN = Uris.LOGIN;
import {Component, ReactNode} from "react";

/**
 * App component.
 */
export default function App() {
    const loggedIn = useLoggedIn();

    /**
     * Protection route component, redirects to login page if not logged in.
     *
     * @param children the children to render
     */
    class ProtectedRoute extends Component<{ children: any }> {
        render() {
            let {children} = this.props;
            return loggedIn ? children : <Navigate to={LOGIN} replace/>;
        }
    }

    return (
        <div className="App">
            <NavBar/>
            <div className="App-content" style={{ paddingTop: '64px', display: 'grid', gridTemplateColumns: '1fr 1fr', gridGap: '16px' }}>
                <Routes>
                    {routes.map((route, index) =>
                        (route.AuthRequired)
                            ?
                            <Route key={index} path={route.path}
                                   element={<ProtectedRoute>route.element</ProtectedRoute>}/>
                            :
                            <Route key={index} path={route.path} element={route.element}/>
                    )}
                </Routes>
            </div>
        </div>
    );
}
