import { useState } from 'react';
import { api } from '../services/api';
import { useNavigate } from 'react-router-dom';

export default function CreatePostPage() {
    const [title, setTitle] = useState('');
    const [body, setBody] = useState('');
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState('');
    const [createdPost, setCreatedPost] = useState(null);
    const navigate = useNavigate();
    
    const handleSubmit = async (event) => {
        event.preventDefault();

        try {
            setLoading(true);
            setError('');
            setCreatedPost(null);

            const newPost = {
                userId: 12,
                title,
                body,
            };

            const { data } = await api.post('/posts', newPost);
            setCreatedPost(data);
            navigate('/posts');
            // clear
            setTitle('');
            setBody('');
        } catch (event) {
            setError('Error: unable to create post.');
            console.error(event);
        } finally {

            setLoading(false);
        }
    };
    if (loading) return <p>Loading…</p>;
    if (error) return <p>{error}</p>;
    return (
        <section>
            <h2>Create Post</h2>

            <form onSubmit={handleSubmit}>
                <div>
                    <label>
                        Title:{' '}
                        <input
                            type="text"
                            value={title}
                            onChange={(event) => setTitle(event.target.value)}
                            required
                        />
                    </label>
                </div>

                <div>
                    <label>
                        Body:{' '}
                        <textarea
                            value={body}
                            onChange={(event) => setBody(event.target.value)}
                            required
                            rows={4}
                            cols={40}
                        />
                    </label>
                </div>

                <button type="submit">Create</button>
            </form>
            <hr />

            {createdPost && (
                <article>
                    <h3>{createdPost.title}</h3>
                    <p>{createdPost.body}</p>
                    <small>
                        User: {createdPost.userId} | ID: {createdPost.id}
                    </small>
                </article>
            )}
        </section>
    );
}
