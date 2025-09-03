import { NavLink } from 'react-router-dom';

export default function Navigation() {
    return (
        <>
            <nav className="navigation">
                <NavLink to="/">Home</NavLink>
                <NavLink to="/posts">Posts</NavLink>
                <NavLink to="/posts/create">Create</NavLink>
            </nav>
        </>
    );
}
