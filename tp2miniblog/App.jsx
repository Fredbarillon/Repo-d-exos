import { api } from './services/api';
import { useEffect, useState } from 'react';
import './App.css';
import { Link, Route, Routes, Outlet } from 'react-router-dom';
import PostsPage from './pages/PostsPage';
import PostsDetailPage from './pages/PostsDetailPage';
import CreatePostPage from './pages/CreatePostPage';
import Navigation from './components/Navigation';

function App() {
    return (
        <>
            <Routes>
                <Route element={<Layout />}>
                    <Route index element={<PostsPage />} />

                    <Route path="posts">
                        <Route index element={<PostsPage />} />
                        <Route path=":id" element={<PostsDetailPage />} />
                        <Route path="create" element={<CreatePostPage />} />
                    </Route>

                    <Route path="*" element={<WrongURL />} />
                </Route>
            </Routes>
            <hr />
        </>
    );
}

function Layout() {
    return (
        <>
            <h1>Mini-Blog</h1>
            <Navigation />
            <main>
                <Outlet />
            </main>
        </>
    );
}

function WrongURL() {
    return <Link to="/">Wrong URL, go back to home</Link>;
}

export default App;
