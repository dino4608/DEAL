'use client';

import { logoutUser } from '@/actions/auth';
import { User } from '@prisma/client';
import Link from 'next/link';
import { useRouter } from 'next/navigation';
import React, { useEffect, useState } from 'react';
import { useShallow } from 'zustand/shallow';
import HeaderSearchBar from './HeaderSearchBar';
import { useCartStore } from '@/stores/cart-store';

const AnnouncementBar = () => {
    return (
        <div className='w-full bg-black py-2'>
            <div className='container mx-auto flex items-center justify-center px-8'>
                <span className='text-center text-sm font-medium tracking-wide text-white'>
                    FREE SHIPPING ON ORDERS OVER $15.00 • FREE RETURNS
                </span>
            </div>
        </div>
    );
}

type HeaderProps = {
    user: Omit<User, 'passwordHash'> | null;
    categorySelector: React.ReactNode;
};

const Header = ({ user, categorySelector }: HeaderProps) => {
    const router = useRouter();

    // Note:
    // amount the component => init states
    // change states => re-render the component
    // unmount the component => destroy states
    const [isOpen, setIsOpen] = useState<boolean>(true);
    const [prevScrollY, setPrevScrollY] = useState<number>(0);

    const { open, getTotalItems } = useCartStore(
        useShallow((state) => ({
            open: state.open,
            getTotalItems: state.getTotalItems
        }))
    );

    // Note:
    // useEffect always runs when mounting
    // useEffect with no dependencies re-runs when the component re-render
    // useEffect with a multi dependencies array re-runs when the dependencies change
    // useEffect with an empty dependencies array doesn't re-run
    useEffect(() => {
        const handleScroll = () => {
            // Note:
            // Although states are updated, handleScroll uses the states at its register time.
            // To use the updated states, please re-register it, please re-run useEffect
            const currentScrollY = window.scrollY;
            const scrolledUp = currentScrollY < prevScrollY;

            if (scrolledUp) {
                setIsOpen(true);
            } else if (currentScrollY > 100) {
                setIsOpen(false);
            }

            setPrevScrollY(currentScrollY);
        };

        setPrevScrollY(window.scrollY);

        // Note: Register an side effect on window when the component amounts and useEffect re-runs
        window.addEventListener('scroll', handleScroll);

        return () => {
            // Note:
            // Clean the side effect on window when the component unmounts or useEffect re-runs
            // When useEffect re-runs, the code inside return runs firstly, then the code above return runs
            // Therefore, the clean up before the register again
            window.removeEventListener('scroll', handleScroll);
        };
    }, [prevScrollY]);

    return (
        <header className='w-full sticky top-0 z-50'>
            <div className={`w-full transform transition-transform duration-300 ease-in-out ${isOpen ? 'translate-y-0' : '-translate-y-full'}`}>
                <AnnouncementBar />
            </div>

            <div className='w-full flex justify-between items-center py-3 sm:py-4 bg-white/80 shadow-sm border-b border-gray-100 backdrop-blur-sm'>
                <div className='flex justify-between items-center container mx-auto px-8'>
                    <div className='flex flex-1 justify-start items-center gap-4 sm:gap-6'>
                        <button className='text-gray-700 hover:text-gray-900 md:hidden'>
                            <svg xmlns='http://www.w3.org/2000/svg' className='h-5 w-5 sm:h-6 sm:w-6' fill='none' viewBox='0 0 24 24' stroke='currentColor'>
                                <path strokeLinecap='round' strokeLinejoin='round' strokeWidth={2} d='M4 6h16M4 12h16M4 18h16' />
                            </svg>
                        </button>

                        <nav className='hidden md:flex gap-4 lg:gap-6 text-sm font-medium'>
                            {categorySelector}
                            <Link href='#'>Shop</Link>
                            <Link href='#'>New Arrivals</Link>
                            <Link href='#'>Sale</Link>
                        </nav>
                    </div>

                    <Link href='#' className='absolute left-1/2 -translate-x-1/2'>
                        <span className='text-xl sm:text-2xl font-bold tracking-tight'>DEAL</span>
                    </Link>

                    <div className='flex flex-1 justify-end items-center gap-2 sm:gap-4'>
                        <HeaderSearchBar />

                        {user ? (
                            <div className='flex items-center gap-2 sm:gap-4'>
                                <span className='text-sm text-gray-700 hidden md:block'>{user.email}</span>
                                <Link
                                    href='#'
                                    className='text-xs sm:text-sm font-medium text-gray-700 hover:text-gray-900'
                                    onClick={async (e) => {
                                        e.preventDefault();
                                        await logoutUser();
                                        router.refresh();
                                    }}
                                >
                                    Sign Out
                                </Link>
                            </div>
                        ) : (
                            <React.Fragment>
                                <Link href='/auth/sign-in' className='text-xs sm:text-sm font-medium text-gray-700 hover:text-gray-900'>
                                    Sign In
                                </Link>
                                <Link href='/auth/sign-up' className='text-xs sm:text-sm font-medium text-gray-700 hover:text-gray-900'>
                                    Sign Up
                                </Link>
                            </React.Fragment>
                        )}

                        <button onClick={() => open()} className='text-gray-700 hover:text-gray-900 relative'>
                            <svg xmlns='http://www.w3.org/2000/svg' className='h-5 w-5 sm:h-6 sm:w-6' fill='none' viewBox='0 0 24 24' stroke='currentColor'>
                                <path strokeLinecap='round' strokeLinejoin='round' strokeWidth={2} d='M16 11V7a4 4 0 00-8 0v4M5 9h14l1 12H4L5 9z' />
                            </svg>
                            <span className='absolute -top-1 -right-1 bg-black text-white text-[10px] sm:text-xs w-3.5 h-3.5 sm:w-4 sm:h-4 rounded-full flex items-center justify-center'>
                                {getTotalItems()}
                            </span>
                        </button>
                    </div>
                </div>
            </div>
        </header>
    );
};

export default Header;