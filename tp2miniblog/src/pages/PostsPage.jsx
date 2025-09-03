import { useEffect, useState } from 'react';
import { api } from '../services/api';
import { Link } from 'react-router-dom';

export default function PostsPage() {
    const [posts, setPosts] = useState([]);
    const [visible, setVisible] = useState(10);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState('');

    useEffect(() => {
        const fetchPosts = async () => {
            try {
                setLoading(true);
                setError('');
                const { data } = await api.get('/posts');
                setPosts(Array.isArray(data) ? data : []);
            } catch (e) {
                setError('Error: unable to fetch the posts.');
                console.error(e);
            } finally {
                setLoading(false);
            }
        };
        fetchPosts();
    }, []);

    if (loading) return <p>Loading…</p>;
    if (error) return <p>{error}</p>;

    const visiblePosts = posts.slice(0, visible);
    const hasMore = visible < posts.length;

    return (
        <section>
            <h2>Posts</h2>

            {visiblePosts.length === 0 && !loading && !error && <p>No posts.</p>}

            <ul>
                {visiblePosts.map((post) => (
                    <li key={post.id}>
                        <h3><Link to={`/posts/${post.id}`}>{post.title}</Link></h3>
                        <p>{post.body.substring(0, 100)}...</p>
                    </li>
                ))}
            </ul>

            <div>
                <button type="button" onClick={() => setVisible((v) => v + 10)}>
                    {hasMore ? 'Load 10 more' : 'No more posts'}
                </button>
            </div>
        </section>
    );
}
