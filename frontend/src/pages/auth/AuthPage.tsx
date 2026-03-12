import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { Mail, Lock, User as UserIcon, ArrowRight, Play, CheckCircle2, AlertCircle } from 'lucide-react';
import { useAuth } from '../../hooks/useAuth';
import { useLocalStorage } from '../../hooks/useLocalStorage';
import type { User } from '../../types';
import { cn } from '../../lib/utils';
import { motion, AnimatePresence } from 'framer-motion';

const AuthPage: React.FC<{ type: 'login' | 'register' }> = ({ type }) => {
  const { login, register } = useAuth();
  const [users, setUsers] = useLocalStorage<User[]>('all_users', []);
  const navigate = useNavigate();

  const [formData, setFormData] = useState({
    username: '',
    email: '',
    password: '',
    confirmPassword: '',
  });

  const [errors, setErrors] = useState<Record<string, string>>({});
  const [serverError, setServerError] = useState('');
  const [isSubmitting, setIsSubmitting] = useState(false);



  const validate = () => {
    const newErrors: Record<string, string> = {};
    if (type === 'register' && !formData.username) newErrors.username = 'Nom d\'utilisateur requis';
    if (!formData.email) {
      newErrors.email = 'Email requis';
    } else if (!/\S+@\S+\.\S+/.test(formData.email)) {
      newErrors.email = 'Format d\'email invalide';
    }
    if (!formData.password) {
      newErrors.password = 'Mot de passe requis';
    } else if (formData.password.length < 6) {
      newErrors.password = 'Minimum 6 caractères';
    }
    if (type === 'register' && formData.password !== formData.confirmPassword) {
      newErrors.confirmPassword = 'Les mots de passe ne correspondent pas';
    }
    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setServerError('');
    if (!validate()) return;

    setIsSubmitting(true);
    await new Promise(resolve => setTimeout(resolve, 1000));

    try {
      if (type === 'register') {
        const userExists = users.some(u => u.email === formData.email);
        if (userExists) {
          setServerError('Cet email est déjà utilisé');
          setIsSubmitting(false);
          return;
        }

        const newUser: User = {
          id: Date.now().toString(),
          username: formData.username,
          email: formData.email,
          password: formData.password,
        };

        setUsers([...users, newUser]);
        register(newUser);
        navigate('/');
      } else {
        const foundUser = users.find(u => u.email === formData.email && u.password === formData.password);
        if (foundUser) {
          login(foundUser);
          navigate('/');
        } else {
          setServerError('Email ou mot de passe incorrect');
        }
      }
    } catch (err) {
      setServerError('Une erreur est survenue');
    } finally {
      setIsSubmitting(false);
    }
  };

  return (
    <div className="relative min-h-screen w-full flex items-center justify-center bg-dark px-4 py-12">
      <div className="relative z-20 w-full max-w-md">
        {/* Logo Section */}
        <div className="mb-10 flex flex-col items-center">
          <Link to="/" className="mb-6 flex items-center gap-3">
            <div className="flex h-12 w-12 items-center justify-center rounded-2xl bg-primary shadow-lg shadow-primary/20">
              <Play className="fill-white text-white" size={26} />
            </div>
            <span className="font-outfit text-3xl font-black text-white tracking-tight">
              STREAM<span className="text-primary font-extralight">HUB</span>
            </span>
          </Link>
          <h1 className="text-center font-outfit text-3xl font-bold text-white">
            {type === 'login' ? 'Connexion' : 'Inscription'}
          </h1>
        </div>

        {/* Form Card */}
        <div className="glass rounded-[2rem] p-8 md:p-10 shadow-2xl">
          <AnimatePresence mode="wait">
            {serverError && (
              <motion.div
                initial={{ opacity: 0, y: -10 }}
                animate={{ opacity: 1, y: 0 }}
                exit={{ opacity: 0, y: -10 }}
                className="mb-8 flex items-center gap-3 rounded-xl bg-primary/10 p-4 text-sm text-primary border border-primary/20"
              >
                <AlertCircle size={18} />
                <span>{serverError}</span>
              </motion.div>
            )}
          </AnimatePresence>

          <form onSubmit={handleSubmit} className="space-y-8">
            {type === 'register' && (
              <div className="space-y-3">
                <div className="relative">
                  <UserIcon className="absolute left-4 top-1/2 -translate-y-1/2 text-gray-500" size={18} />
                  <input
                    type="text"
                    className={cn(
                      "auth-input pl-12",
                      errors.username && "border-primary/50"
                    )}
                    placeholder="Nom d'utilisateur"
                    value={formData.username}
                    onChange={(e) => setFormData({ ...formData, username: e.target.value })}
                  />
                </div>
                {errors.username && <p className="text-xs text-primary font-medium ml-1">{errors.username}</p>}
              </div>
            )}

            <div className="space-y-3">
              <div className="relative">
                <Mail className="absolute left-4 top-1/2 -translate-y-1/2 text-gray-500" size={18} />
                <input
                  type="email"
                  className={cn(
                    "auth-input pl-12",
                    errors.email && "border-primary/50"
                  )}
                  placeholder="Email"
                  value={formData.email}
                  onChange={(e) => setFormData({ ...formData, email: e.target.value })}
                />
              </div>
              {errors.email && <p className="text-xs text-primary font-medium ml-1">{errors.email}</p>}
            </div>

            <div className="space-y-3">
              <div className="relative">
                <Lock className="absolute left-4 top-1/2 -translate-y-1/2 text-gray-500" size={18} />
                <input
                  type="password"
                  className={cn(
                    "auth-input pl-12",
                    errors.password && "border-primary/50"
                  )}
                  placeholder="Mot de passe"
                  value={formData.password}
                  onChange={(e) => setFormData({ ...formData, password: e.target.value })}
                />
              </div>
              {errors.password && <p className="text-xs text-primary font-medium ml-1">{errors.password}</p>}
            </div>

            {type === 'register' && (
              <div className="space-y-3">
                <div className="relative">
                  <CheckCircle2 className="absolute left-4 top-1/2 -translate-y-1/2 text-gray-500" size={18} />
                  <input
                    type="password"
                    className={cn(
                      "auth-input pl-12",
                      errors.confirmPassword && "border-primary/50"
                    )}
                    placeholder="Confirmer mot de passe"
                    value={formData.confirmPassword}
                    onChange={(e) => setFormData({ ...formData, confirmPassword: e.target.value })}
                  />
                </div>
              </div>
            )}

            <button
              type="submit"
              disabled={isSubmitting}
              className="group relative flex w-full items-center justify-center rounded-2xl bg-primary py-4 text-sm font-bold text-white shadow-xl transition-all hover:bg-primary-hover active:scale-[0.98] disabled:opacity-50 mt-4"
            >
              {isSubmitting ? (
                <div className="h-5 w-5 animate-spin rounded-full border-2 border-white/20 border-t-white" />
              ) : (
                <div className="flex items-center gap-2">
                  <span>{type === 'login' ? 'Connexion' : 'S\'inscrire'}</span>
                  <ArrowRight size={18} className="transition-transform group-hover:translate-x-1" />
                </div>
              )}
            </button>
          </form>

          <p className="mt-10 text-center text-sm text-gray-400">
            {type === 'login' ? (
              <> Pas de compte ? <Link to="/register" className="font-bold text-primary hover:underline">S'inscrire</Link></>
            ) : (
              <> Déjà inscrit ? <Link to="/login" className="font-bold text-primary hover:underline">Connexion</Link></>
            )}
          </p>
        </div>
      </div>
    </div>
  );
};

export default AuthPage;
