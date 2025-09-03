import { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { api } from '../services/api';

export default function PostsDetailPage() {
    const { id } = useParams();
    const [post, setPost] = useState(null);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState('');

    useEffect(() => {
        const fetchPost = async () => {
            try {
                setLoading(true);
                setError('');
                const { data } = await api.get(`/posts/${id}`);
                setPost(data);
            } catch (e) {
                setError('Error: unable to fetch the post.');
                console.error(e);
            } finally {
                setLoading(false);
            }
        };

        fetchPost();
    }, [id]);

    if (loading) return <p>Loading…</p>;
    if (error) return <p>{error}</p>;
    if (!post) return <p>No post found.</p>;

    return (
        <>
        <h1>Détails du poste {post.id}</h1>
        <article>
            <h2>{post.title}</h2>
            <p>{post.body}</p>
        </article>
        </>
    );
}
