import * as React from 'react'
import {useState} from 'react'
import AppBar from '@mui/material/AppBar'
import Toolbar from '@mui/material/Toolbar'
import IconButton from '@mui/material/IconButton'
import Typography from '@mui/material/Typography'
import Menu from '@mui/material/Menu'
import MenuIcon from '@mui/icons-material/Menu'
import Container from '@mui/material/Container'
import MenuItem from '@mui/material/MenuItem'
import {useLocation, useNavigate} from 'react-router-dom'
import {useLoggedIn, useSession, useSessionManager} from "../Utils/Session"
import {pages} from "./utils/pagesConfig";


/**
 * Navigation Bar component
 */
export default function NavBar() {
    const navigate = useNavigate();
    const location = useLocation();
    const loggedIn = useLoggedIn();
    const sessionManager = useSessionManager();
    const session = useSession();

    const handleLogout = async () => {
        if (!session) {
            navigate('/');
            return;
        }
        //
        //
        //
        sessionManager.clearSession();
        navigate('/');
    };

    const [anchorElNav, setAnchorElNav] = useState<null | HTMLElement>(null);

    const handleOpenNavMenu = (event: React.MouseEvent<HTMLElement>) => {
        setAnchorElNav(event.currentTarget);
    };

    const handleCloseNavMenu = () => {
        setAnchorElNav(null);
    };

    const filteredPages = pages.filter(page => page.href !== location.pathname);

    return (
        <AppBar position="static" sx={{backgroundColor: '#f5f5f5'}}>
            <Container maxWidth="xl">
                <Toolbar disableGutters>
                    <IconButton
                        size="large"
                        aria-label="open navigation menu"
                        aria-controls="menu-appbar"
                        aria-haspopup="true"
                        onClick={handleOpenNavMenu}
                        color="inherit"
                        sx={{color: '#333'}}
                    >
                        <MenuIcon/>
                    </IconButton>
                    <Menu
                        id="menu-appbar"
                        anchorEl={anchorElNav}
                        anchorOrigin={{
                            vertical: 'bottom',
                            horizontal: 'left',
                        }}
                        keepMounted
                        transformOrigin={{
                            vertical: 'top',
                            horizontal: 'left',
                        }}
                        open={Boolean(anchorElNav)}
                        onClose={handleCloseNavMenu}
                        sx={{
                            '.MuiPaper-root': {
                                backgroundColor: '#f5f5f5',
                                color: '#555',
                            }
                        }}
                    >
                        {filteredPages.map((page) => (
                            (!page.AuthRequired || loggedIn) && (
                                <MenuItem
                                    key={page.name}
                                    onClick={() => {
                                        handleCloseNavMenu();
                                        if (page.name === 'Logout') {
                                            handleLogout();
                                        } else {
                                            navigate(page.href);
                                        }
                                    }}
                                    sx={{color: '#555'}}
                                >
                                    <Typography textAlign="center">{page.name}</Typography>
                                </MenuItem>
                            )
                        ))}
                    </Menu>
                    <Typography
                        variant="h6"
                        noWrap
                        component="div"
                        sx={{
                            flexGrow: 1,
                            display: 'flex',
                            justifyContent: 'center',
                            fontFamily: 'monospace',
                            fontWeight: 700,
                            letterSpacing: '.3rem',
                            color: '#555',
                            textDecoration: 'none',
                            cursor: 'pointer'
                        }}
                    >
                        Generate Entity-Relation Diagrams
                    </Typography>
                </Toolbar>
            </Container>
        </AppBar>
    );
}