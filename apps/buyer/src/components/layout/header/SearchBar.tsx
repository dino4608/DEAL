'use client';

import { Search } from 'lucide-react';
import Form from 'next/form';
import React from 'react';

const SearchBar = () => {
    return (
        <Form
            action='/search'
            className='relative'
        >
            <div className='absolute inset-y-0 left-0 pl-2 lg:pl-4 flex items-center pointer-events-none'>
                <Search className='w-5 text-black' />
            </div>

            <input
                type='text'
                name='query'
                placeholder='Trung Nhân đẹp trai lắm á...'
                className="
                    w-40 md:w-60 lg:w-80 xl:w-120 pl-7 lg:pl-10 pr-17 lg:pr-20 py-1 bg-white text-sm text-black leading-loose border-2 border-black rounded-full
                    focus:outline-none transition-colors"
            />

            <button className='absolute inset-y-0 right-0 pr-1'>
                <div className='text-[var(--dino-red-1)] pr-2 lg:pr-4'>
                    Search
                </div>
            </button>
        </Form>
    );
};

export default SearchBar;
