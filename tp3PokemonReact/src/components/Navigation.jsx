import { Link } from 'react-router-dom';

function Navigation() {
    return (
        <nav className="navigation">
            <Link to="/" style={{ marginRight: '20px' }}>
                Home
            </Link>
            <Link to="/profile" style={{ marginRight: '20px' }}>
                Profile
            </Link>
            <Link to="/pokemon/favorites" style={{ marginRight: '20px' }}>
                Favorites
            </Link>
            <Link to="/profile/team" style={{ marginRight: '20px' }}>
                My Team
            </Link>
        </nav>
    );
}

export default Navigation;
