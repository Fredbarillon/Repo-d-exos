import { useEffect, useState } from 'react';
import { api } from '../services/api';

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

  const visiblePosts = posts.slice(0, visible);
  const hasMore = visible < posts.length;

  return (
    <section >
      <h2>Posts</h2>

      {loading && posts.length === 0 && <p>Loading…</p>}
      {error && <p>{error}</p>}

      {visiblePosts.length === 0 && !loading && !error && <p>No posts.</p>}

      <ul>
        {visiblePosts.map((post) => (
          <li key={post.id}>
            <h3>{post.title}</h3>
            <p>{post.body.substring(0, 100)}...</p>
          </li>
        ))}
      </ul>

      <div>
        <button
          type="button"
          onClick={() => setVisible((v) => v + 10)}
        >
          {hasMore ? ('Load 10 more') : 'No more posts'}
        </button>
      </div>
    </section>
  );
}
